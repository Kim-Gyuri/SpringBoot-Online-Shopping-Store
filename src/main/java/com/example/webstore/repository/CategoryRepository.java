package com.example.webstore.repository;

import com.example.webstore.domain.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ItemCategory, Long> {
    ItemCategory findByCode(String code);
}
