package com.example.webstore;

import com.example.webstore.domain.*;
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

   // private final ItemService itemService;
    //private final UserService userService;
    //private final CartService cartService;

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

            sellBook(user);
        }

        private void sellBook(User user) {
            Item item = new Item(user, "1984", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img = new ItemImg("origin.jpg", "1984.jpg", "c:save/1984.jpg", "Y",item);
            em.persist(item);
            em.persist(img);

            Item item2 = new Item(user, "And Then There Were None", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img2 = new ItemImg("origin.jpg", "And Then There Were None.jpg", "c:save/And Then There Were None.jpg", "Y",item2);
            em.persist(item2);
            em.persist(img2);

            Item item3 = new Item(user, "Anne of Green Gables", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img3 = new ItemImg("origin.jpg", "Anne of Green Gables.jpg", "c:save/Anne of Green Gables.jpg", "Y",item3);
            em.persist(item3);
            em.persist(img3);

            Item item4 = new Item(user, "Damian", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img4 = new ItemImg("origin.jpg", "Damian.jpg", "c:save/Damian.jpg", "Y",item4);
            em.persist(item4);
            em.persist(img4);

            Item item5 = new Item(user, "Little Women", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img5 = new ItemImg("origin.jpg", "Little Women.jpg", "c:save/Little Women.jpg", "Y",item5);
            em.persist(item5);
            em.persist(img5);

            Item item6 = new Item(user, "MOMO", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img6 = new ItemImg("origin.jpg", "MOMO.jpg", "c:save/MOMO.jpg", "Y",item6);
            em.persist(item6);
            em.persist(img6);

            Item item7 = new Item(user, "My Sweet Orange Tree", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img7 = new ItemImg("origin.jpg", "My Sweet Orange Tree.jpg", "c:save/My Sweet Orange Tree.jpg", "Y",item7);
            em.persist(item7);
            em.persist(img7);

            Item item8 = new Item(user, "Peter Pan", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img8 = new ItemImg("origin.jpg", "Peter Pan.jpg", "c:save/Peter Pan.jpg", "Y",item8);
            em.persist(item8);
            em.persist(img8);

            Item item9 = new Item(user, "Seven Years of Darkness", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img9 = new ItemImg("origin.jpg", "Seven Years of Darkness.jpg", "c:save/Seven Years of Darkness.jpg", "Y",item9);
            em.persist(item9);
            em.persist(img9);

            Item item10 = new Item(user, "Tara Duncan", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img10 = new ItemImg("origin.jpg", "Tara Duncan.jpg", "c:save/Tara Duncan.jpg", "Y",item10);
            em.persist(item10);
            em.persist(img10);

            Item item11 = new Item(user, "The Blue Bird for Children", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img11 = new ItemImg("origin.jpg", "The Blue Bird for Children.jpg", "c:save/The Blue Bird for Children.jpg", "Y",item11);
            em.persist(item11);
            em.persist(img11);

            Item item12 = new Item(user, "The Diary of Anne Frank", 10000, 10, "최상", "BOOK", ItemSellStatus.SELL);
            ItemImg img12 = new ItemImg("origin.jpg", "The Diary of Anne Frank.jpg", "c:save/The Diary of Anne Frank.jpg", "Y",item12);
            em.persist(item12);
            em.persist(img12);
        }

        public void dbInit2() {
            Address addressA = new Address("진주", "2", "2222");
            User user = new User( "test4","test4!","userD", "bambi05@naver.com", addressA);
            em.persist(user);

            sellMusic(user);
        }

        private void sellMusic(User user) {
            Item item = new Item(user, "Edith Piaf", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img = new ItemImg("origin.jpg", "Edith Piaf.jpg", "c:save/Edith Piaf.jpg", "Y",item);
            em.persist(item);
            em.persist(img);


            Item item2 = new Item(user, "Warner Classics", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img2 = new ItemImg("origin.jpg", "Warner Classics.jpg", "c:save/Warner Classics.jpg", "Y",item2);
            em.persist(item2);
            em.persist(img2);

            Item item3 = new Item(user, "Utopia", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img3 = new ItemImg("origin.jpg", "Utopia.jpg", "c:save/Utopia.jpg", "Y",item3);
            em.persist(item3);
            em.persist(img3);

            Item item4 = new Item(user, "Utada Hikaru", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img4 = new ItemImg("origin.jpg", "Utada Hikaru.jpg", "c:save/Utada Hikaru.jpg", "Y",item4);
            em.persist(item4);
            em.persist(img4);

            Item item5 = new Item(user, "totoro", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img5 = new ItemImg("origin.jpg", "totoro.jpg", "c:save/totoro.jpg", "Y",item5);
            em.persist(item5);
            em.persist(img5);

            Item item6 = new Item(user, "sm", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img6 = new ItemImg("origin.jpg", "sm.jpg", "c:save/sm.jpg", "Y",item6);
            em.persist(item6);
            em.persist(img6);

            Item item7 = new Item(user, "Sia", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img7 = new ItemImg("origin.jpg", "Sia.jpg", "c:save/Sia.jpg", "Y",item7);
            em.persist(item7);
            em.persist(img7);

            Item item8 = new Item(user, "pororo", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img8 = new ItemImg("origin.jpg", "pororo.jpg", "c:save/pororo.jpg", "Y",item8);
            em.persist(item8);
            em.persist(img8);

            Item item9 = new Item(user, "NewJeans", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img9 = new ItemImg("origin.jpg", "NewJeans.jpg", "c:save/NewJeans.jpg", "Y",item9);
            em.persist(item9);
            em.persist(img9);

            Item item10 = new Item(user, "Miriam Makeba", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img10 = new ItemImg("origin.jpg", "Miriam Makeba.jpg", "c:save/Miriam Makeba.jpg", "Y",item10);
            em.persist(item10);
            em.persist(img10);

            Item item11 = new Item(user, "La La Land OST by Justin Hurwitz", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img11 = new ItemImg("origin.jpg", "La La Land OST by Justin Hurwitz.jpg", "c:save/La La Land OST by Justin Hurwitz.jpg", "Y",item11);
            em.persist(item11);
            em.persist(img11);

            Item item12 = new Item(user, "John Coltrane", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img12 = new ItemImg("origin.jpg", "John Coltrane.jpg", "c:save/John Coltrane.jpg", "Y",item12);
            em.persist(item12);
            em.persist(img12);

            Item item13 = new Item(user, "BLACKPINK", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img13 = new ItemImg("origin.jpg", "BLACKPINK.jpg", "c:save/BLACKPINK.jpg", "Y",item13);
            em.persist(item13);
            em.persist(img13);
        }


        public void dbInit3(){
            Address addressA = new Address("진주", "2", "2222");
            Address addressB = new Address("서울", "1", "1111");

            User userA = new User( "test","test!","userA", "mimi03@naver.com", addressA);
            User userB = new User("test2", "test2!","userB", "nana05@gmail.com", addressB);

            em.persist(userA);
            em.persist(userB);

            Item item1 = new Item(userB, "Ariana Grande", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            Item item2 = new Item(userB, "BAEK HYUN", 10000, 10, "최상", "MUSIC", ItemSellStatus.SELL);
            ItemImg img1 = new ItemImg("origin.jpg", "Ariana Grande.jpg", "c:save/Ariana Grande.jpg", "Y",item1);
            ItemImg img2 = new ItemImg("origin.jpg", "BAEK HYUN.jpg", "c:save/BAEK HYUN.jpg", "Y",item2);

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
