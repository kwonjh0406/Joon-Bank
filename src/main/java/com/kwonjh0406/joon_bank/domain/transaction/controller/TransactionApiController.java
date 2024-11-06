package com.kwonjh0406.joon_bank.domain.transaction.controller;

import com.kwonjh0406.joon_bank.domain.account.entity.Account;
import com.kwonjh0406.joon_bank.domain.account.repository.AccountRepository;
import com.kwonjh0406.joon_bank.domain.transaction.dto.TransferParam;
import com.kwonjh0406.joon_bank.domain.transaction.entity.Transaction;
import com.kwonjh0406.joon_bank.domain.transaction.repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionApiController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/api/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferParam transferParam) {


        /**
         * Logging
         */
        System.out.println("transferParam.fromAccount" + transferParam.getFromAccount());
        System.out.println("transferParam.toAccount" + transferParam.getToAccount());
        System.out.println("transferParam.amount" + transferParam.getAmount());



        Account account = accountRepository.findByAccountNumber(transferParam.getFromAccount());
        account.setBalance(account.getBalance() - transferParam.getAmount());
        accountRepository.save(account);

        account = accountRepository.findByAccountNumber(transferParam.getToAccount());
        account.setBalance(account.getBalance() + transferParam.getAmount());
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .fromAccount(transferParam.getFromAccount())
                .toAccount(transferParam.getToAccount())
                .amount(transferParam.getAmount())
                .build();

        transactionRepository.save(transaction);

        return ResponseEntity.ok(transaction.toString());


    }
}
