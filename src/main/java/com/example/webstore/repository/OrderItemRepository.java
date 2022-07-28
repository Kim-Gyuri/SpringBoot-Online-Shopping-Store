package com.example.webstore.repository;

import com.example.webstore.domain.Item;
import com.example.webstore.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByItem(Item item);
}
