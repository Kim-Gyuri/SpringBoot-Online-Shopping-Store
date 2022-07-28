package com.example.webstore.controller.dto;

import com.example.webstore.domain.User;
import lombok.Data;

@Data
public class ItemSearchCondition {
    private String itemName;
    private String categoryType;
    private User loginMember;
}
