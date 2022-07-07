package com.zurum.lanefinance.dtos.response;

import com.zurum.lanefinance.constants.WalletType;
import lombok.Data;

@Data
public class RegistrationResponseDto {
    private Long newAccountNumber;
    private String accountType = WalletType.SAVINGS.toString();

    public RegistrationResponseDto(Long newAccountNumber) {
        this.newAccountNumber = newAccountNumber;
    }
}
