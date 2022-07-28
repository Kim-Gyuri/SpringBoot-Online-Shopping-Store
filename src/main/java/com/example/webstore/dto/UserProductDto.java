package com.example.webstore.dto;

import lombok.Data;

@Data
public class UserProductDto {

    private Long itemId;
    private String itemName;
    private int price;
    private int quantity;
    private String imgName;

    public void updateUserProduct(Long itemId, String itemName, int price, int quantity, String imgName) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imgName = imgName;
    }
}
