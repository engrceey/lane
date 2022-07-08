package com.zurum.lanefinance.service;

import com.zurum.lanefinance.dtos.request.ActivateAccountRequestDto;
import com.zurum.lanefinance.dtos.response.FetchAccountResponseDto;
import com.zurum.lanefinance.entity.Wallet;

public interface WalletService {
    FetchAccountResponseDto fetchAccount(long accountNumber);
    boolean activateAccount(ActivateAccountRequestDto activateAccountRequestDto);
    Wallet getLoggedInUserAccountDetails();

}
