package com.zurum.lanefinance.service;

import com.zurum.lanefinance.dtos.request.DepositAccountRequestDto;
import com.zurum.lanefinance.dtos.request.TransferFundRequestDto;
import com.zurum.lanefinance.dtos.request.WithdrawFundRequestDto;
import com.zurum.lanefinance.dtos.response.DepositResponseDto;
import com.zurum.lanefinance.dtos.response.TransferResponseDto;
import com.zurum.lanefinance.dtos.response.WithdrawFundResponseDto;

public interface TransactionService {
    DepositResponseDto depositFunds(DepositAccountRequestDto depositAccountRequestDto);
    TransferResponseDto transferFunds(TransferFundRequestDto transferFundRequestDto);
    WithdrawFundResponseDto withdrawFunds(WithdrawFundRequestDto withdrawFundRequestDto);
}
