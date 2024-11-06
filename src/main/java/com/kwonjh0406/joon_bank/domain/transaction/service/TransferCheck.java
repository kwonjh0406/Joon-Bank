package com.kwonjh0406.joon_bank.domain.transaction.service;

public enum TransferCheck {
    ACCOUNT_NOT_FOUND,   // 상대 계좌 존재하지 않음
    LIMIT_EXCEEDED,      // 이체 한도 초과
    INSUFFICIENT_BALANCE, // 계좌 잔액 부족
    OK
}
