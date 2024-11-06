package com.kwonjh0406.joon_bank.domain.transaction.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class TransferParam {

    private Long fromAccount;

    @Digits(integer = 19, fraction = 0, message = "계좌번호는 19자리 이하의 숫자만 입력 가능합니다.")
    @NotNull(message = "상대 계좌번호를 입력하세요.")
    private Long toAccount;

    @Digits(integer = 19, fraction = 0, message = "보낼 금액은 19자리 이하의 숫자만 입력 가능합니다.")
    @NotNull(message = "보낼 금액을 입력하세요.")
    private Long amount;
}
