package com.example.webstore;

import com.example.webstore.domain.*;
import com.example.webstore.service.CartService;
import com.example.webstore.service.ItemService;
import com.example.webstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Slf4j
@Profile("local")
@Component
@RequiredArgsConstructor
public class Init {

    private final InitService initService;
    private final ItemService itemService;
    private final UserService userService;
    private final CartService cartService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
      initService.dbInit2();
        initService.dbInit3();


    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Address addressA = new Address("진주", "2", "2222");
            User user = new User( "test3","test3!","userC", "karis99@naver.com", addressA);
            em.persist(user);

            for (int ii = 0; ii < 14; ii++) {
                Item item = new Item(user, "itemNameA" + ii, 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
                ItemImg img = new ItemImg("origin.jpg", "store.jpg", "c:save/store.jpg", "Y",item);

                em.persist(item);
                em.persist(img);
            }
        }

        public void dbInit2() {
            Address addressA = new Address("진주", "2", "2222");
            User user = new User( "test4","test4!","userD", "bambi05@naver.com", addressA);
            em.persist(user);

            for (int ii = 0; ii < 13; ii++) {
                Item item = new Item(user, "itemNameB" + ii, 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
                ItemImg img = new ItemImg("origin.jpg", "store.jpg", "c:save/store.jpg", "Y",item);
                em.persist(item);
                em.persist(img);
            }

        }

        public void dbInit3(){
            Address addressA = new Address("진주", "2", "2222");
            Address addressB = new Address("서울", "1", "1111");

            User userA = new User( "test","test!","userA", "mimi03@naver.com", addressA);
            User userB = new User("test2", "test2!","userB", "nana05@gmail.com", addressB);

            em.persist(userA);
            em.persist(userB);

            Item item1 = new Item(userB, "spring5", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            Item item2 = new Item(userB, "mvc2", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img1 = new ItemImg("origin.jpg", "store.jpg", "c:save/store.jpg", "Y",item1);
            ItemImg img2 = new ItemImg("origin.jpg", "store.jpg", "c:save/store.jpg", "Y",item2);

            em.persist(item1);
            em.persist(img1);
            em.persist(item2);
            em.persist(img2);

            OrderItem orderItem1 = new OrderItem(item1, 5);
            OrderItem orderItem2 = new OrderItem(item2, 2);
            orderItem1.orderAmount(5);
            orderItem2.orderAmount(2);

            Cart cart = Cart.createCartV2(userA, orderItem1, orderItem2);

            em.persist(cart);

        }


    }

}
