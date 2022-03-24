package com.example.frontend.controller;

import com.example.frontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/demo")
    public String getDefault() {
        return "Working....";
    }
    @GetMapping("/token")
    public String registerUserAccount() {
        return userService.getAccessToken();
    }
}
