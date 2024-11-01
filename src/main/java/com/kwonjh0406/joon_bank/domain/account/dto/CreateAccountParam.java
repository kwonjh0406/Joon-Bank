package com.kwonjh0406.joon_bank.domain.account.dto;


import com.kwonjh0406.joon_bank.domain.account.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountParam {

    private AccountType accountType;
    private String accountName;

}
