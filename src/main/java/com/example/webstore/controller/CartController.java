package com.example.webstore.controller;

import com.example.webstore.argumentResolver.Login;
import com.example.webstore.domain.Cart;
import com.example.webstore.domain.OrderItem;
import com.example.webstore.domain.User;
import com.example.webstore.dto.CartInfoDto;
import com.example.webstore.service.CartService;
import com.example.webstore.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;

    @PostMapping("/products/{itemId}")
    public String mergeCart(@Login User loginMember, @PathVariable Long itemId, @RequestParam int count) {

        Long cartId = cartService.mergeCart(loginMember, itemId, count);

        Cart savedCart = cartService.findById(cartId);
        log.info("Order count={}", count);
        for (OrderItem orderItem : savedCart.getOrderItems()) {
            log.info("stock Item={}", orderItem.getItem().getStockQuantity());
            log.info("orderAmount={}", orderItem.getOrderPrice());
        }

        return "redirect:/loginInfo";
    }


    @GetMapping("/loginInfo")
    public String getCart(Model model, @Login User loginMember) {

        if (loginMember== null) {
            model.addAttribute("message", "no User");
        }
        model.addAttribute("member", loginMember);
        log.info("get loginMember={}",loginMember.getLoginId());

        List<CartInfoDto> cartList = cartService.getCartList(loginMember);
        model.addAttribute("cartList", cartList);
        for (CartInfoDto cartDto : cartList) {
            log.info("cartDto name={}", cartDto.getItem().getItemName());
        }

        return "cart/cartListV2";
    }

    @GetMapping("/cart/delete/{orderItemId}")
    public String deleteOrderItem(@PathVariable("orderItemId") Long orderItemId) {
        cartService.deleteCart(orderItemId);

        return "redirect:/loginInfo";
    }

}
