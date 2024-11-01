package com.kwonjh0406.joon_bank.global;

import com.kwonjh0406.joon_bank.domain.account.AccountRepository;
import com.kwonjh0406.joon_bank.domain.user.User;
import com.kwonjh0406.joon_bank.domain.user.UserRepository;
import com.kwonjh0406.joon_bank.domain.user.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Long user_id = customUserDetails.getId();

        model.addAttribute("accounts", accountRepository.findByUserId(user_id));

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
