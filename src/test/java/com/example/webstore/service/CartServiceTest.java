package com.example.webstore.service;

import com.example.webstore.domain.Item;
import com.example.webstore.domain.ItemSellStatus;
import com.example.webstore.domain.User;
import com.example.webstore.dto.CartInfoDto;
import com.example.webstore.dto.ItemFormDto;
import com.example.webstore.dto.UserFormDto;
import com.example.webstore.repository.ItemRepository;
import com.example.webstore.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired CartService cartService;
    @Autowired ItemRepository itemRepository;
    @Autowired UserRepository userRepository;

    public User createCustomerTest() {
        UserFormDto dto = new UserFormDto();
        dto.setLoginId("wolf27");
        dto.setPassword("1234@");
        dto.setName("wolf");
        dto.setEmail("wolf1004@naver.com");
        dto.setCity("서울");
        dto.setStreet("마포구 합정동");
        dto.setZipcode("4015");

        return userRepository.save(dto.UserEntity());
    }

    public Item createItemTest() throws Exception {

        ItemFormDto dto = new ItemFormDto();
        dto.setItemName("테스트 상품명");
        dto.setCategoryType("BOOK");
        dto.setItemType("최상");
        dto.setPrice(10000);
        dto.setQuantity(100);
        dto.setStatus(ItemSellStatus.SELL);

        return itemRepository.save(dto.toEntity());

    }


    @Test
    @DisplayName("장바구니 담기 테스트")
    void addCart() throws Exception {

        User customer = createCustomerTest();
        Item item = createItemTest();
        int orderCount = 10;

        cartService.mergeCart(customer, item.getId(), 10);

        List<CartInfoDto> cartList = cartService.getCartList(customer);
        for (CartInfoDto cartInfoDto : cartList) {
            assertEquals(cartInfoDto.getItem(), item);
            assertEquals(cartInfoDto.getCount(), orderCount);
            assertEquals(90, item.getStockQuantity());
        }


    }



}