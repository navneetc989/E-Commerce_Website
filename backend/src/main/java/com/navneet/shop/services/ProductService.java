package com.navneet.shop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.navneet.shop.dto.product.RegisterProductDTO;
import com.navneet.shop.entities.product.Product;
import com.navneet.shop.entities.product.ProductJpaEntity;
import com.navneet.shop.entities.productcat.ProductCategory;
import com.navneet.shop.exceptions.DataNotFoundException;
import com.navneet.shop.repositories.product.ProductCategoryRepository;
import com.navneet.shop.repositories.product.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private ProductRepository repository;

    private ProductCategoryRepository productCategoryRepository;

    public ProductService(ProductRepository repository,
	    ProductCategoryRepository productCategoryRepository) {
	this.repository = repository;
	this.productCategoryRepository = productCategoryRepository;
    }

    public Page<Product> findAllPaged(Pageable pageable) {
	return repository.findAll(pageable)
		.map(ProductJpaEntity::toAggregate);
    }

    public Page<Product> findByCategoryId(Long id,
	    Pageable pageable) {
	Page<Product> entity;
	if (checkIfNullOrNegative(id)) {
	    entity = repository.findAll(pageable)
		    .map(ProductJpaEntity::toAggregate);
	} else {
	    entity = repository.findByCategoryId(id, pageable)
		    .map(ProductJpaEntity::toAggregate);
	}
	return entity;
    }

    private boolean checkIfNullOrNegative(Long entity) {
	return entity == null || entity < 0;
    }

    @Transactional
    public Page<Product> findByName(String name, Pageable pageable) {
	return repository
		.findByNameContainingIgnoreCase(name, pageable)
		.map(ProductJpaEntity::toAggregate);
    }

    @Transactional
    public Product findById(Long id) {
	return getProduct(id);
    }

    @Transactional
    public Product saveProductJson(RegisterProductDTO productDto) {
	ProductCategory category = getProductCategory(productDto);
	Product entity = Product.create(category, productDto.getSku(),
		productDto.getName(), productDto.getDescription(),
		productDto.getUnitPrice(), productDto.getImageUrl(),
		productDto.getActive(), productDto.getUnitsInStock());
	return repository.save(ProductJpaEntity.from(entity))
		.toAggregate();
    }

    @Transactional
    public Boolean deleteById(Long id) {

	if (repository.existsById(id)) {
	    repository.deleteById(id);
	}

	return true;
    }

    @Transactional
    public Product updateProductJson(Long id,
	    RegisterProductDTO productDto) {
	Product entity = getProduct(id);
	ProductCategory category = getProductCategory(productDto);
	entity.update(category, productDto.getSku(),
		productDto.getName(), productDto.getDescription(),
		productDto.getUnitPrice(), productDto.getImageUrl(),
		productDto.getActive(), productDto.getUnitsInStock());

	repository.save(ProductJpaEntity.from(entity)).toAggregate();
	return entity;
    }

    private ProductCategory getProductCategory(
	    RegisterProductDTO productDto) {
	return productCategoryRepository
		.findByCategoryName(productDto.getCategoryName())
		.orElseThrow(() -> new DataNotFoundException(
			"'categoryName' not found with name: %s"
				.formatted(productDto
					.getCategoryName())));
    }

    private Product getProduct(Long id) {
	return repository.findById(id)
		.orElseThrow(() -> new DataNotFoundException(
			"'product' not found with id: %s"
				.formatted(id)))
		.toAggregate();
    }
}
