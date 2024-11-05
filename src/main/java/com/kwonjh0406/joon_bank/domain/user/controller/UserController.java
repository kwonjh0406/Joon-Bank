package com.kwonjh0406.joon_bank.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "auth/loginForm";
    }

    @GetMapping("/signup")
    public String signup() {
        return "auth/signupForm";
    }

}
