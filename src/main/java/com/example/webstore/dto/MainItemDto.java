package com.example.webstore.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MainItemDto {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private String imgName;
    private String itemType;
    private String categoryType;

    @QueryProjection
    public MainItemDto(Long id, String itemName, Integer price, Integer quantity, String imgName, String itemType, String categoryType) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imgName = imgName;
        this.itemType = itemType;
        this.categoryType = categoryType;
    }
}
