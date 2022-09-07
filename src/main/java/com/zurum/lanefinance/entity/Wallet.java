package com.zurum.lanefinance.entity;

import com.zurum.lanefinance.constants.WalletCurrency;
import com.zurum.lanefinance.constants.WalletType;
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
@Table(name = "wallet",
        indexes = @Index(name = "account_number_index", columnList = "account_number"),
        uniqueConstraints= @UniqueConstraint(columnNames = "account_number")
)
public class Wallet extends BaseEntity{

    @Column(name = "account_number", length = 10)
    private long accountNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_type", length = 10)
    @Builder.Default
    private WalletType walletType = WalletType.SAVINGS;

    @Column(name = "wallet_balance")
    @Builder.Default
    private BigDecimal accountBalance = BigDecimal.valueOf(0.0);

    @Column(name = "wallet_pin", length = 4)
    private String pin;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "wallet_currency")
    @Builder.Default
    private WalletCurrency walletCurrency = WalletCurrency.EUR;

    @Column(name = "activated")
    private boolean isActivated = false;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private AppUser user;

}