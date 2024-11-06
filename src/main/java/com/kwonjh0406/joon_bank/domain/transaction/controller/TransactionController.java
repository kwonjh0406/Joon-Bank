package com.kwonjh0406.joon_bank.domain.transaction.controller;

import com.kwonjh0406.joon_bank.domain.account.entity.Account;
import com.kwonjh0406.joon_bank.domain.account.repository.AccountRepository;
import com.kwonjh0406.joon_bank.domain.transaction.service.TransferLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TransactionController {

    @Autowired
    TransferLimitService transferLimitService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/account/{accountNumber}/transfer")
    public String transfer(@PathVariable("accountNumber") Long accountNumber, Model model) {

        Account account =  accountRepository.findByAccountNumber(accountNumber);

        Long limit = transferLimitService.getTransferLimit(accountNumber);

        model.addAttribute("limit", limit);
        model.addAttribute("account", account);

        return "transfer";
    }
}
