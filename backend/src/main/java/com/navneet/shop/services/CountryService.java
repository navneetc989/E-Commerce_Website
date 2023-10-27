package com.navneet.shop.services;

import com.navneet.shop.dto.controller.location.CountryDTO;
import com.navneet.shop.entities.location.Country;
import com.navneet.shop.exceptions.DataNotFoundException;
import com.navneet.shop.repositories.location.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository repository;

    @Transactional(readOnly = true)
    public Page<CountryDTO> findAllPaged(Pageable pageable) {
        Page<Country> found = repository.findAll(pageable);
        return found.map(x -> new CountryDTO(x));
    }

    @Transactional(readOnly = true)
    public CountryDTO findOneById(Long id) {
        Optional<Country> obj = repository.findById(id);
        Country country = obj.orElseThrow(() -> new DataNotFoundException("Country not founded."));
        return new CountryDTO(country);
    }
}
