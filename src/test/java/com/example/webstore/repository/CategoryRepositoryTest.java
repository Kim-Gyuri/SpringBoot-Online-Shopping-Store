package com.example.webstore.repository;

import com.example.webstore.domain.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class CategoryRepositoryTest {
    
    @Autowired
    ItemRepository itemRepository;
    
    @Test
    @Transactional
    void 카테고리로_찾기() {
        List<Item> items = itemRepository.findAllByCategoryType("MUSIC");
        for (Item item : items) {
            System.out.println("item.getCategoryType() = " + item.getCategoryType());
            System.out.println("item.getItemName() = " + item.getItemName());
        }
    }

}