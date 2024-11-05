package com.kwonjh0406.joon_bank.domain.account.controller;

import com.kwonjh0406.joon_bank.domain.account.AccountRepository;
import com.kwonjh0406.joon_bank.domain.account.dto.TransactionResponse;
import com.kwonjh0406.joon_bank.domain.transaction.entity.Transaction;
import com.kwonjh0406.joon_bank.domain.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/account/create")
    public String createAccount() {
        return "createAccount";
    }

    @GetMapping("/account/{id}")
    public String account(@PathVariable("id") Long accountNumber, Model model) {

        Long accountNum = accountRepository.findById(accountNumber).get().getAccountNumber();

        List<Transaction> list =  transactionRepository.findByAccount(accountNum);



        List<TransactionResponse> transactionResponses = new ArrayList<>();

        for(Transaction transaction : list){

            if(transaction.getFromAccount().equals(accountNum)){
                TransactionResponse transactionResponse = new TransactionResponse();
                transactionResponse.setAmount(-transaction.getAmount());
                transactionResponse.setTimestamp(transaction.getCreatedTime());
                String name = accountRepository.findByAccountNumber(transaction.getToAccount()).getUser().getName();
                transactionResponse.setName(name);
                transactionResponses.add(transactionResponse);
            }
            else {
                TransactionResponse transactionResponse = new TransactionResponse();
                transactionResponse.setAmount(transaction.getAmount());
                transactionResponse.setTimestamp(transaction.getCreatedTime());
                String name = accountRepository.findByAccountNumber(transaction.getFromAccount()).getUser().getName();
                transactionResponse.setName(name);
                transactionResponses.add(transactionResponse);
            }

        }

        model.addAttribute("transactions", transactionResponses);






        model.addAttribute("accountNumber", accountNumber);
        return "account";

    }

}
