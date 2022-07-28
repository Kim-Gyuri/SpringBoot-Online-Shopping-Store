package com.example.webstore.repository;

import com.example.webstore.domain.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
    List<ItemImg> findAllByItem_id(Long itemId);
}
