package com.example.webstore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder(builderMethodName = "cartBuilder")
    public Cart(User user, List<OrderItem> orderItems) {
        this.user = user;
        this.orderItems = orderItems;
    }

    public static Cart createCart(OrderItem... orderItems) {
        Cart cart = new Cart();
        for (OrderItem orderItem : orderItems) {
            cart.addOrderItem(orderItem);
        }
        return cart;
    }

    public static Cart createCartV2(User user, OrderItem... orderItems) {
        Cart cart = new Cart();
        cart.setUpUser(user);
        for (OrderItem orderItem : orderItems) {
            cart.addOrderItem(orderItem);
        }
        return cart;
    }


    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.addCart(this);
    }

    public void setUpUser(User user) {
        this.user = user;
    }

   // public void removeFromCart(OrderItem orderItem) {
    //    orderItems.remove(orderItem);
   // }
}
