package com.navneet.shop.services;

import com.navneet.shop.dto.controller.OrderDTO;
import com.navneet.shop.entities.order.Order;
import com.navneet.shop.repositories.order.OrderRepository;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(final OrderRepository repository) {
	this.repository = Objects.requireNonNull(repository);
    }

    public Page<OrderDTO> findByEmail(String email,
	    Pageable pageable) {

	Page<Order> orders = repository
		.findByUserEmailOrderByDateCreatedDesc(email,
			pageable);
	return orders.map(OrderDTO::new);
    }
}
