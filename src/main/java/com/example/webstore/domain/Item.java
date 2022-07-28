package com.example.webstore.domain;

import com.example.webstore.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String itemName;
    private Integer price;
    private Integer stockQuantity;

    private String itemType; //상품등급
    private String categoryType; //카테고리
    private ItemSellStatus status; //판매상태

    @Builder(builderMethodName = "itemBuilder")
    public Item(String itemName, Integer price, Integer stockQuantity, String itemType, String categoryType, ItemSellStatus status) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.itemType = itemType;
        this.categoryType = categoryType;
        this.status = status;
    }

    @Builder(builderMethodName = "secondItemBuilder")
    public Item(User user, String itemName, Integer price, Integer stockQuantity, String itemType, String categoryType, ItemSellStatus status) {
        this.user = user;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.itemType = itemType;
        this.categoryType = categoryType;
        this.status = status;
    }


    public static Item createItem(User user, String itemName, Integer price, Integer stockQuantity, String itemType, String categoryType, ItemSellStatus status) {
        Item item = new Item(itemName, price, stockQuantity, itemType, categoryType, ItemSellStatus.SELL);
        item.setUpUser(user);
        return item;
    }

    public void removeStock(int quantity) {
        int restQuantity = this.stockQuantity - quantity;
        if (restQuantity < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restQuantity;
    }

    public void cancelCart(int quantity) {
        int rest = this.stockQuantity + quantity;
        this.stockQuantity = rest;
    }

    public void checkStatus() {
        int restQuantity = this.stockQuantity;
        if (restQuantity < 0) {
            throw new NotEnoughStockException("no stock");
        }
        this.status = ItemSellStatus.SOLD_OUT;
    }

    public void setUpUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Item Info {" + "id=" + id + ", name=" + itemName + ", price=" + price + ", stockQuantity =" + stockQuantity + ", itemType =" + itemType + ", categoryType =" + categoryType + ", status=" + status + '}';
    }

}
