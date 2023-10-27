package com.navneet.shop.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navneet.shop.entities.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
