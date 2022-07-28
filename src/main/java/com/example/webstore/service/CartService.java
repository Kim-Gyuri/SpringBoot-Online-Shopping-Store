package com.example.webstore.service;

import com.example.webstore.domain.Cart;
import com.example.webstore.domain.Item;
import com.example.webstore.domain.OrderItem;
import com.example.webstore.domain.User;
import com.example.webstore.dto.CartInfoDto;
import com.example.webstore.repository.CartRepository;
import com.example.webstore.repository.ItemRepository;
import com.example.webstore.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemService itemService;

    public Cart findById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다."));
        return cart;
    }

    public List<CartInfoDto> getCartList(User user) {
        List<Cart> carts = cartRepository.findByUser(user);

        List<CartInfoDto> dtoList = new ArrayList<>();
        for (Cart cart : carts) {
            for (OrderItem orderItem : cart.getOrderItems()) {
                CartInfoDto cartDto = new CartInfoDto();

                cartDto.updateCartInfo(orderItem.getId(), user.getLoginId(), orderItem.getItem(), orderItem.getCount(), orderItem.getOrderPrice());
                log.info("update Dto={}", cartDto.getItem().getStockQuantity());
                dtoList.add(cartDto);

            }
        }
        return dtoList;
    }



    @Transactional
    public Long mergeCart(User user, Long itemId, int count) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));

        OrderItem orderItem = OrderItem.orderItemBuilder()
                .item(item)
                .count(count)
                .build();

        orderItem.orderAmount(count);
        Cart cart = Cart.createCartV2(user, orderItem);

        cartRepository.save(cart);
        return cart.getId();
    }

    @Transactional
    public void deleteCart(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));

        orderItem.getItem().cancelCart(orderItem.getCount());
        orderItemRepository.delete(orderItem);

    }

    /*

    @Transactional
    public List<Cart> getCart(User user) {
        List<Cart> carts = cartRepository.findByUser(user);

        for (Cart cart : carts) {
            for (OrderItem orderItem : cart.getOrderItems()) {
                log.info("orderItem List={}", orderItem.getItem().getItemName());
            }
        }
        return carts;
    }

    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

     */


}
