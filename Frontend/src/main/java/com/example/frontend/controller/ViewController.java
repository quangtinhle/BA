package com.example.frontend.controller;

import com.example.frontend.Entity.Greeting;
import com.example.frontend.Model.Credentials;
import com.example.frontend.Model.User;
import com.example.frontend.Model.UserDTO;
import com.example.frontend.convert.ReciverUserConvert;
import com.example.frontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {


@Autowired UserService userService;

    @GetMapping("/registration")
    public String getRegisterForm(Model model) {
        model.addAttribute("messsage", "REGISTER FORM");
        model.addAttribute("user", new UserDTO());
        return "registrationForm";
    }


    @GetMapping("/userinformation")
    public String getDefaultView(Model model) {
        return "userinformation";
    }

/*
    @PostMapping("/signin")
    public String getLoginForm(@ModelAttribute UserDTO userDTO, Model model) {
        model.addAttribute("messsage", "SIGN IN");
        userService.createUser(userDTO);
        //model.addAttribute("user", new User());
        System.out.println("Hallo chung may");
        return "signinForm";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }*/



}
