package com.kwonjh0406.joon_bank.domain.transaction.service;

import com.kwonjh0406.joon_bank.domain.account.Account;
import com.kwonjh0406.joon_bank.domain.account.AccountRepository;
import com.kwonjh0406.joon_bank.domain.transaction.entity.TransferLimit;
import com.kwonjh0406.joon_bank.domain.transaction.repository.TransactionRepository;
import com.kwonjh0406.joon_bank.domain.transaction.repository.TransferLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

            transferLimit = TransferLimit.builder()
                    .accountNumber(accountNumber)
                    .limitAmount(amount)
                    .build();

            transferLimitRepository.save(transferLimit);

            return amount;
        }

        return transferLimit.getLimitAmount();

    }
}
