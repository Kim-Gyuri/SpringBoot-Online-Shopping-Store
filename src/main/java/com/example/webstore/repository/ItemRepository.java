package com.example.webstore.repository;

import com.example.webstore.domain.Item;
import com.example.webstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
    List<Item> findAllByCategoryType(String code);
    Item findByItemName(String itemName);
    List<Item> findAllByUser(User user);
    List<Item> findAllById(Long id);
    void deleteByItemName(String itemName);

}
