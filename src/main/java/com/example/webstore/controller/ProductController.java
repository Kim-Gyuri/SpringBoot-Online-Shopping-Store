package com.example.webstore.controller;

import com.example.webstore.dto.ItemFormDto;
import com.example.webstore.dto.ProductInfo;
import com.example.webstore.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ItemService itemService;

    @GetMapping("/products/{itemId}")
    public String showOne(@PathVariable Long itemId, Model model) {

        ItemFormDto product = itemService.getItemDetail(itemId);

        ProductInfo info = new ProductInfo();
        info.updateProductInfo(product.getItemName(), product.getPrice(), product.getQuantity(), product.getItemType(), product.getCategoryType(), product.getStatus(), product.getItemImgDtoList());

        model.addAttribute("product", info);
        return "product/productInfoV2";
    }




}
