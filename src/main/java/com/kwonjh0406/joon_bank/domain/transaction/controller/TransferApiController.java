package com.kwonjh0406.joon_bank.domain.transaction.controller;

import com.kwonjh0406.joon_bank.domain.transaction.service.TransferCheck;
import com.kwonjh0406.joon_bank.domain.transaction.dto.TransferParam;
import com.kwonjh0406.joon_bank.domain.transaction.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferApiController {

    private final TransferService transferService;

    @Autowired
    TransferApiController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/api/transfer/check")
    public ResponseEntity<String> transferCheck(@Valid @RequestBody TransferParam transferParam) {

        TransferCheck transferCheck = transferService.checkTransfer(transferParam);

        return switch (transferCheck) {
            case OK -> ResponseEntity.ok("OK");
            case LIMIT_EXCEEDED -> ResponseEntity.badRequest().body("LimitExceeded");
            case INSUFFICIENT_BALANCE -> ResponseEntity.badRequest().body("InsufficientBalance");
            case ACCOUNT_NOT_FOUND -> ResponseEntity.badRequest().body("상대 계좌가 존재하지 않습니다.");
        };

    }

//    @PostMapping("/transfer")
//    public ResponseEntity<String> transfer(TransferParam transferParam) {
//
//    }


}
