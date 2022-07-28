package com.example.webstore.dto;

import com.example.webstore.domain.Address;
import com.example.webstore.domain.User;
import lombok.Data;

@Data
public class UserFormDto {

    private Long id;

    private String loginId;
    private String password;

    private String name;
    private String email;

    private String city;
    private String street;
    private String zipcode;

    public Address addressEntity() {
        return Address.addressBuilder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();
    }

    public User UserEntity() {
        return User.userBuilder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .address(addressEntity())
                .build();
    }
}
