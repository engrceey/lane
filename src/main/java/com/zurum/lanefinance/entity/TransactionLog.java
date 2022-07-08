package com.zurum.lanefinance.entity;

import com.zurum.lanefinance.constants.TransactionStatus;
import com.zurum.lanefinance.constants.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "transactions")
public class TransactionLog extends BaseEntity {

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_id", nullable = false )
    private String transactionId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "transaction_type", length = 20)
    private TransactionType transactionType;

    @Column(name = "sender", length = 50)
    private String sender;

    @Column(name = "sender_account_number")
    private long senderAccountNumber;

    @Column(name = "receiver", length = 50)
    private String receiver;

    @Column(name = "receiver_account_number")
    private long receiverAccountNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "transaction_status", length = 50)
    @Builder.Default
    private TransactionStatus transactionStatus = TransactionStatus.PENDING;

}
