package com.navneet.shop.repositories.location;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navneet.shop.entities.location.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
