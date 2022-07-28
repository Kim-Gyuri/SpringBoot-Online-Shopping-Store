package com.example.webstore.dto;

import com.example.webstore.domain.Item;
import com.example.webstore.domain.ItemSellStatus;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemFormDto {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private String itemType;
    private String categoryType;
    private ItemSellStatus status;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    public Item toEntity() {
        return Item.itemBuilder()
                .itemName(itemName)
                .price(price)
                .stockQuantity(quantity)
                .itemType(itemType)
                .categoryType(categoryType)
                .status(status)
                .build();
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemFormDto of(Item item) {
       return modelMapper.map(item,ItemFormDto.class);
    }


}
