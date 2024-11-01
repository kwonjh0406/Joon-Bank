package com.kwonjh0406.joon_bank.domain.account;

import com.kwonjh0406.joon_bank.domain.account.dto.CreateAccountParam;
import com.kwonjh0406.joon_bank.domain.user.User;
import com.kwonjh0406.joon_bank.domain.user.UserRepository;
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

    public ResponseEntity<String> createAccount(CreateAccountParam createAccountParam) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        User user = userRepository.findByUsername( userDetails.getUsername());

        System.out.println(user.getUsername());

        Account account = Account.builder()
                .accountName(createAccountParam.getAccountName())
                .accountType(createAccountParam.getAccountType())
                .user(user)
                .balance(1000000L)
                .accountNumber(100L)
                .build();

        accountRepository.save(account);

        return ResponseEntity.ok().body("계좌가 개설되었습니다.");
    }
}
