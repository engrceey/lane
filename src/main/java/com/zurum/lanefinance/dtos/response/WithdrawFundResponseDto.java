package com.zurum.lanefinance.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WithdrawFundResponseDto {
    private long accountNum;
    private String name;
    private String status;
    private boolean isSuccessful;
}
