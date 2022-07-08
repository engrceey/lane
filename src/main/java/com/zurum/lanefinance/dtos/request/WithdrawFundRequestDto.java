package com.zurum.lanefinance.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawFundRequestDto {
    private BigDecimal amount;
}
