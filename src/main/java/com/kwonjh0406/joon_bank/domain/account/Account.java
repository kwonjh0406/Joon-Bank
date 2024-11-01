package com.kwonjh0406.joon_bank.domain.account;

import com.kwonjh0406.joon_bank.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String accountName = "새 계좌";

    private Long accountNumber;

    private Long balance = 1000000L;

    private Long dailyTransferLimit = 1000000L;

    private AccountType accountType;

    @CreationTimestamp
    private Timestamp createdTime;

}
