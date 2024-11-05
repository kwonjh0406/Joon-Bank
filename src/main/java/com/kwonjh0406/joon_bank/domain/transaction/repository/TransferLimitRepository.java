package com.kwonjh0406.joon_bank.domain.transaction.repository;

import com.kwonjh0406.joon_bank.domain.transaction.entity.TransferLimit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransferLimitRepository extends CrudRepository<TransferLimit, Long> {

    public TransferLimit findByAccountNumber(Long accountNumber);
}
