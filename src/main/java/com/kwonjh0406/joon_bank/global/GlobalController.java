package com.kwonjh0406.joon_bank.global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/account")
    public String account() {
        return "account";
    }

    @GetMapping("/add-account")
    public String addAccount() {
        return "add-account";
    }

}
