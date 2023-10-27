package com.navneet.shop.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navneet.shop.entities.order.Order;

public interface OrdersRepository extends JpaRepository<Order, Long> {

}
