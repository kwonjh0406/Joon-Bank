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
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Builder.Default
    private String accountName = "새 계좌"; // 별명을 지정하지 않을 시 "새 계좌"로 별명 설정

    @Column(nullable = false)
    private Long accountNumber;

    @Column(nullable = false)
    @Builder.Default
    private Long balance = 1000000L; // 계좌 최초 생성 시 기본 잔고 100만원 설정

    @Column(nullable = false)
    @Builder.Default
    private Long dailyTransferLimit = 1000000L; // 계좌 최초 생성 시 일일 이체한도 100만원 설정

    @Column(nullable = false)
    private AccountType accountType;

    @CreationTimestamp
    private Timestamp createdTime;

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
