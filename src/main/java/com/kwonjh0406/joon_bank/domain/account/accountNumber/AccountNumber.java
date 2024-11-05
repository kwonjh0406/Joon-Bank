package com.kwonjh0406.joon_bank.domain.account.accountNumber;

import com.kwonjh0406.joon_bank.domain.account.AccountType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AccountNumber {

    @Id
    private Long id;

    private Long countAccounts;

    private Long checking;

    private Long saving;

    private Long installmentSaving;

}
