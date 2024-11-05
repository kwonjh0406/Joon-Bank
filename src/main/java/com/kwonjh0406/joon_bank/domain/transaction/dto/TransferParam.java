package com.kwonjh0406.joon_bank.domain.transaction.dto;

import lombok.Getter;

@Getter
public class TransferParam {

    private Long fromAccount;

    private Long toAccount;

    private Long amount;
}
