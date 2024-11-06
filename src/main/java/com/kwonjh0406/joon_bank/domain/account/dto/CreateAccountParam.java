package com.kwonjh0406.joon_bank.domain.account.dto;


import com.kwonjh0406.joon_bank.domain.account.entity.AccountType;
import lombok.Getter;

@Getter
public class CreateAccountParam {

    private AccountType accountType;

    private String accountName;

    public void defaultAccountName(String accountName) {
        this.accountName = accountName;
    }

}
