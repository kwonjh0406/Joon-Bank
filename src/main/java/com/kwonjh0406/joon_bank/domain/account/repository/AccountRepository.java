package com.kwonjh0406.joon_bank.domain.account.repository;

import com.kwonjh0406.joon_bank.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long user_id);
    Account findByAccountNumber(Long account_number);
}
