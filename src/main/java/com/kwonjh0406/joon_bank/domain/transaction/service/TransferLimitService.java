package com.kwonjh0406.joon_bank.domain.transaction.service;

import com.kwonjh0406.joon_bank.domain.account.entity.Account;
import com.kwonjh0406.joon_bank.domain.account.repository.AccountRepository;
import com.kwonjh0406.joon_bank.domain.transaction.entity.TransferLimit;
import com.kwonjh0406.joon_bank.domain.transaction.repository.TransactionRepository;
import com.kwonjh0406.joon_bank.domain.transaction.repository.TransferLimitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TransferLimitService {

    private final TransferLimitRepository transferLimitRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    TransferLimitService(TransferLimitRepository transferLimitRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.transferLimitRepository = transferLimitRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Long getTransferLimit(Long accountNumber) {

        TransferLimit transferLimit = transferLimitRepository.findByAccountNumber(accountNumber);

        if (transferLimit == null) {
            Account account = accountRepository.findByAccountNumber(accountNumber);
            Long amount = account.getDailyTransferLimit();

            /*
            당일 이체 금액 제외
             */

            transferLimit = TransferLimit.builder()
                    .accountNumber(accountNumber)
                    .limitAmount(amount)
                    .build();

            transferLimitRepository.save(transferLimit);

            return amount;
        }

        return transferLimit.getLimitAmount();

    }

    // 00시마다 Redis 이체 한도 데이터 초기화
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteAllTransferLimits() {
        transferLimitRepository.deleteAll();
    }
}
