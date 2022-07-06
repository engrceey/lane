package com.zurum.lanefinance.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KycLevel {
    TIER_1(20_000.00), TIER_2(50_000.00), TIER_3(100_000.00);

    private final Double withdrawalLimit;

}
