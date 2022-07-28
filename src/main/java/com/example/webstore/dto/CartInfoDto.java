package com.example.webstore.dto;

import com.example.webstore.domain.Item;
import com.example.webstore.domain.OrderItem;
import lombok.Data;

@Data
public class CartInfoDto {
    private Long orderItemId;
    private String loginId;
    private Item item;
    private int count;
    private int orderPrice;

    public void updateCartInfo(Long orderItemId, String loginId, Item item, int count, int orderPrice) {
        this.orderItemId = orderItemId;
        this.loginId = loginId;
        this.item = item;
        this.count = count;
        this.orderPrice = orderPrice;
    }

    public static OrderItem toEntity(Item item, int count) {
        return OrderItem.orderItemBuilder()
                .item(item)
                .count(count)
                .build();
    }
}
