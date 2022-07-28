package com.example.webstore.repository;

import com.example.webstore.domain.Cart;
import com.example.webstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
}
