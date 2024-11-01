package com.kwonjh0406.joon_bank.domain.account;

import com.kwonjh0406.joon_bank.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long user_id);
}
