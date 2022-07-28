package com.example.webstore.dto;

import com.example.webstore.domain.ItemSellStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductInfo {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private String itemType;
    private String categoryType;
    private ItemSellStatus status;

    private int count;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();


    public void updateProductInfo(String itemName, Integer price, Integer quantity, String itemType, String categoryType, ItemSellStatus status, List<ItemImgDto> itemImgDtoList) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.itemType = itemType;
        this.categoryType = categoryType;
        this.status = status;
        this.itemImgDtoList = itemImgDtoList;
    }
}
