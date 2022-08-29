package com.example.webstore.service;

import com.example.webstore.domain.User;
import com.example.webstore.dto.UserFormDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    public UserFormDto createUserTest() {
        UserFormDto dto = new UserFormDto();
        dto.setLoginId("nana20");
        dto.setPassword("1234@");
        dto.setName("faker");
        dto.setEmail("karis99@naver.com");
        dto.setCity("서울");
        dto.setStreet("마포구 합정동");
        dto.setZipcode("1120");

        return dto;
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveUserTest() {
        UserFormDto dto = createUserTest();
        Long savedId = userService.signUp(dto);

        User findUser = userService.findOne(dto.getLoginId());

        Assertions.assertEquals(dto.getLoginId(), findUser.getLoginId());


    }

}