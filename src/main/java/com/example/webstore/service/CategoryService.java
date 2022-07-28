package com.example.webstore.service;

import com.example.webstore.domain.ItemCategory;
import com.example.webstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<ItemCategory> findAll() {
        return categoryRepository.findAll();
    }

    public ItemCategory findById(Long categoryId) {
        ItemCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board id= " + categoryId));
        return category;
    }

    public ItemCategory findByCode(String code) {
        return categoryRepository.findByCode(code);
    }

    public ItemCategory save(ItemCategory itemCategory) {
        return categoryRepository.save(itemCategory);
    }

    @Transactional(readOnly = true)
    public Page<ItemCategory> findListByCategoryType(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize());
        return categoryRepository.findAll(pageable);
    }
}
