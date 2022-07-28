package com.example.webstore.dto;

import com.example.webstore.domain.Item;
import lombok.Data;

@Data
public class ItemInfo {

    private String repImgYn;
    private Item item;
}
