package com.navneet.shop.repositories.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.navneet.shop.entities.location.State;

public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByCountryCode(@Param("code") String countryCode);
}
