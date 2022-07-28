package com.example.webstore.service;

import com.example.webstore.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CartServiceTest {

    @Test
    @Transactional
    void 장바구니_생성() {

        Address addressA = new Address("진주", "2", "2222");
        Address addressB = new Address("서울", "1", "1111");

        User userA = new User( "test","test!","userA", "mimi03@naver.com", addressA);
        User userB = new User("test2", "test2!","userB", "nana05@gmail.com", addressB);



        Item item1 = new Item("spring5", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
        Item item2 = new Item("mvc2", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
        ItemImg img1 = new ItemImg("origin.jpg", "store.jpg", "c:save/store.jpg", "Y",item1);
        ItemImg img2 = new ItemImg("origin.jpg", "store.jpg", "c:save/store.jpg", "Y",item2);



        OrderItem orderItem1 = new OrderItem(item1, 5);
        OrderItem orderItem2 = new OrderItem(item2, 2);

        Cart cart = Cart.createCartV2(userA, orderItem1, orderItem2);

        for (OrderItem orderItem : cart.getOrderItems()) {
            System.out.println("orderItem = " + orderItem.getItem().getItemName());
        }

    }

}