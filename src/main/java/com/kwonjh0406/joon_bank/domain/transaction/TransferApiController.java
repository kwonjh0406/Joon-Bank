package com.kwonjh0406.joon_bank.domain.transaction;

import com.kwonjh0406.joon_bank.domain.transaction.dto.TransferParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferApiController {

    private final TransferService transferService;

    @Autowired
    TransferApiController(TransferService transferService) {
        this.transferService = transferService;
    }

//    @PostMapping("/transfer")
//    public ResponseEntity<String> transfer(TransferParam transferParam) {
//
//    }


}
