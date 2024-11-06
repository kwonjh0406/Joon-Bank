package com.kwonjh0406.joon_bank.domain.account.service;

import com.kwonjh0406.joon_bank.domain.account.entity.Account;
import com.kwonjh0406.joon_bank.domain.account.repository.AccountRepository;
import com.kwonjh0406.joon_bank.domain.accountNumber.AccountNumberingService;
import com.kwonjh0406.joon_bank.domain.account.dto.CreateAccountParam;
import com.kwonjh0406.joon_bank.domain.user.entity.User;
import com.kwonjh0406.joon_bank.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountNumberingService accountNumberingService;

    public ResponseEntity<String> createAccount(CreateAccountParam createAccountParam) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername());

        System.out.println(createAccountParam.getAccountName());

        if(createAccountParam.getAccountName().isBlank()){
            createAccountParam.defaultAccountName("새 계좌");
        }

        Account account = Account.builder()
                .accountName(createAccountParam.getAccountName())
                .accountType(createAccountParam.getAccountType())
                .user(user)
                .accountNumber(accountNumberingService.generateAccountNumber(createAccountParam.getAccountType()))
                .build();

        accountRepository.save(account);

        return ResponseEntity.ok().body("계좌가 개설되었습니다.");
    }
}
