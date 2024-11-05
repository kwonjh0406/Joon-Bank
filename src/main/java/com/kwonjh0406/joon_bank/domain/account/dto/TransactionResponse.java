package com.kwonjh0406.joon_bank.domain.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TransactionResponse {

    String name;

    Timestamp timestamp;

    Long amount;
}
