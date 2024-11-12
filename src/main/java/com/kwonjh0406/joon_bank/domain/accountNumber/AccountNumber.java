package com.kwonjh0406.joon_bank.domain.accountNumber;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AccountNumber {

    /**
     * 단일 레코드의 테이블이다.
     * 계좌의 현황을 관리하기 위한 데이터이며, 계좌 번호 발급 과정에서도 사용된다.
     */

    @Id
    private Long id; // 1로 고정

    private Long countAccounts; // 전체 생성된 계좌의 수

    private Long checking; // 전체 생성된 입출금 계좌의 수

    private Long saving; // 전체 생성된 예금 계좌의 수

    private Long installmentSaving; // 전체 생성된 적금 계좌의 수

}
