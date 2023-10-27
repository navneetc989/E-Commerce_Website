package com.navneet.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navneet.shop.entities.role.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
