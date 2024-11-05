package com.kwonjh0406.joon_bank.domain.transaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.redis.core.RedisHash;

import java.sql.Timestamp;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromAccount;

    private Long toAccount;

    private Long amount;

    @CreationTimestamp
    private Timestamp createdTime;

    @RedisHash
    public static class TransferLimit {

        @Id
        private Long accountNumber;

        private Long TransferLimit;
    }
}

