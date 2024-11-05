package com.kwonjh0406.joon_bank.domain.account.controller;

import com.kwonjh0406.joon_bank.domain.account.AccountService;
import com.kwonjh0406.joon_bank.domain.account.dto.CreateAccountParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountApiController {

    AccountService accountService;

    @Autowired
    AccountApiController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountParam createAccountParam) {
        return accountService.createAccount(createAccountParam);


    }

}