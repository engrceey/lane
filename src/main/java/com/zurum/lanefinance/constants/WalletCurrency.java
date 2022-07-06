package com.zurum.lanefinance.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WalletCurrency {
    NGN("naira"), USD("dollar"), GBP("british_pound"), EUR("euro");

    private final String currency;
}