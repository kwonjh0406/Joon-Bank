package com.kwonjh0406.joon_bank.domain.transaction;

import com.kwonjh0406.joon_bank.domain.transaction.dto.TransferParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class TransferService {


    /*
    1. Redis( 계좌 번호 : 당일 남은 한도 )에서 이체 가능 금액 조회
        1.1 Redis에 데이터가 없는 경우 데이터를 넣어줌.
    2. 잔액과 비교 했을 때 이체 가능한지 여부 확인
    3. 상대 게좌가 존재하는지 확인
    4. 정상인 경우로 반환
     */

}
