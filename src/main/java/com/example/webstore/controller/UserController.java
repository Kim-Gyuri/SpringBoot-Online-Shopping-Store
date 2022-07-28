package com.example.webstore.controller;

import com.example.webstore.argumentResolver.Login;
import com.example.webstore.domain.SessionConst;
import com.example.webstore.domain.User;
import com.example.webstore.dto.LoginFormDto;
import com.example.webstore.dto.UserFormDto;
import com.example.webstore.dto.UserMainItemDto;
import com.example.webstore.service.ItemService;
import com.example.webstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ItemService itemService;

    @GetMapping("/register")
    public String signUpForm(Model model) {
        model.addAttribute("userForm", new UserFormDto());
        return "user/registerFormV2";
    }

    @PostMapping("/register")
    public String signUp(UserFormDto userFormDto) {
        userService.signUp(userFormDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String signInForm(@ModelAttribute("loginForm") LoginFormDto form) {
        return "login/loginFormV2";
    }

    @PostMapping("/login")
    public String signIn(@Valid @ModelAttribute LoginFormDto form, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginFormV2";
        }

        User loginUser = userService.signIn(form);

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 다릅니다.");
            return "login/loginFormV2";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginUser);

       // return "redirect:" + redirectURL;
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }

    @GetMapping("/user/Info")
    public String UserInfo(@Login User loginMember, Model model) {
        model.addAttribute("member", loginMember);
        log.info("user address ={}", loginMember.getAddress().getCity());

        return "user/userInfo";
    }

    @GetMapping("/user/product")
    public String UserProductInfo(@Login User loginMember, Model model) {
        List<UserMainItemDto> products = userService.findAllByUser(loginMember);
        model.addAttribute("products", products);
        return "user/userProduct";
    }


   // @GetMapping("/user/product/delete/{itemId}")
    public String deleteOrderItem(@PathVariable("itemId") Long itemId) {

        log.info("delete Item={}", itemService.findById(itemId).getItemName());
        userService.deleteV3(itemId);
        return "redirect:/user/product";
    }


}
