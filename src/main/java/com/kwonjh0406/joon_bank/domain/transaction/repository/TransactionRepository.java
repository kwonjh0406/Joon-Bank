package com.kwonjh0406.joon_bank.domain.transaction.repository;

import com.kwonjh0406.joon_bank.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.fromAccount = :accountId OR t.toAccount = :accountId")
    List<Transaction> findByAccount(@Param("accountId") Long accountId);
}
