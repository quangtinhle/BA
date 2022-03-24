package com.example.frontend.controller;

import com.example.frontend.Entity.Greeting;
import com.example.frontend.Entity.User;
import com.example.frontend.Model.SignInForm;
import com.example.frontend.service.UserService;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Demo {


@Autowired UserService userService;

    @GetMapping("/registration")
    public String getRegisterForm(Model model) {
        model.addAttribute("messsage", "REGISTER FORM");
        return "registrationForm";
    }

    @GetMapping("/signin")
    public String getLoginForm(Model model) {
        model.addAttribute("messsage", "SIGN IN");
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
    }



}
