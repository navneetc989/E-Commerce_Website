package com.navneet.shop.repositories.location;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navneet.shop.entities.location.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
