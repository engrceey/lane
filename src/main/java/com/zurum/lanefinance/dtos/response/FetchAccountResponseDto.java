package com.zurum.lanefinance.dtos.response;

import com.zurum.lanefinance.constants.WalletType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FetchAccountResponseDto {
    private long accountNumber;
    private WalletType accountType;
    private BigDecimal accountBalance;
    private boolean isActivated;
}
