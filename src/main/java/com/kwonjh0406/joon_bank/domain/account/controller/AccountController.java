package com.kwonjh0406.joon_bank.domain.account.controller;

import com.kwonjh0406.joon_bank.domain.account.entity.Account;
import com.kwonjh0406.joon_bank.domain.account.repository.AccountRepository;
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

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    AccountController(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/account/create")
    public String createAccount() {
        return "createAccount";
    }

    @GetMapping("/account/{accountNumber}")
    public String account(@PathVariable("accountNumber") Long accountNumber, Model model) {

        Account account = accountRepository.findByAccountNumber(accountNumber);

        List<Transaction> list =  transactionRepository.findByAccount(accountNumber);

        // TransactionResponse 는 거래 내역 페이지의 사용자에게 필요한 정보만 담고 있음: 상대 이름, 거래 금액, 거래 날짜
        List<TransactionResponse> transactionResponses = new ArrayList<>();

        for(Transaction transaction : list){
            // 내가 보낸 쪽이면 TransactionResponse(받는 쪽 이름, 거래 금액 * -1, 거래 날짜)
            if(transaction.getFromAccount().equals(accountNumber)){
                TransactionResponse transactionResponse = new TransactionResponse();
                transactionResponse.setAmount(-transaction.getAmount());
                transactionResponse.setTimestamp(transaction.getCreatedTime());
                String name = accountRepository.findByAccountNumber(transaction.getToAccount()).getUser().getName();
                transactionResponse.setName(name);
                transactionResponses.add(transactionResponse);
            }
            // 내가 받는 쪽이면 TransactionResponse(보낸 쪽 이름, 거래 금액 * +1, 거래 날짜)
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
        model.addAttribute("account", account);

        return "account";
    }

}
