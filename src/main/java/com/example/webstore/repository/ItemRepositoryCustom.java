package com.example.webstore.repository;

import com.example.webstore.controller.dto.ItemSearchCondition;
import com.example.webstore.dto.MainItemDto;
import com.example.webstore.dto.UserMainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {
    Page<MainItemDto> searchPageSort(ItemSearchCondition condition, Pageable pageable);
    Page<MainItemDto> categoryPageSort(String code, ItemSearchCondition condition, Pageable pageable);
    List<UserMainItemDto> sortByUser();


}
