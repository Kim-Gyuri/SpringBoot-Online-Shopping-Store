package com.example.webstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemType {
    private String code;
    private String typeStatus;
}
