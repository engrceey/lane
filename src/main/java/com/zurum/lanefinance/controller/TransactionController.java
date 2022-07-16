package com.zurum.lanefinance.controller;

import com.zurum.lanefinance.dtos.request.DepositAccountRequestDto;
import com.zurum.lanefinance.dtos.request.TransferFundRequestDto;
import com.zurum.lanefinance.dtos.request.WithdrawFundRequestDto;
import com.zurum.lanefinance.dtos.response.ApiResponse;
import com.zurum.lanefinance.dtos.response.DepositResponseDto;
import com.zurum.lanefinance.dtos.response.TransferResponseDto;
import com.zurum.lanefinance.dtos.response.WithdrawFundResponseDto;
import com.zurum.lanefinance.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(tags = "Transaction Controller")
@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit-fund")
    @ApiOperation(value = "deposit funds",authorizations = { @Authorization(value="Bearer")},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = DepositResponseDto.class)
    public ResponseEntity<ApiResponse<DepositResponseDto>> depositFunds(@RequestBody @Valid final DepositAccountRequestDto depositAccountRequestDto) {
        log.info("controller depositFunds- for :: [{}]", depositAccountRequestDto.getReceiverAccountNumber() );
        DepositResponseDto response = transactionService.depositFunds(depositAccountRequestDto);
        return ResponseEntity.ok(ApiResponse.buildSuccessTxn(response));
    }

    @PostMapping("/withdraw-fund")
    @ApiOperation(value = "withdraw funds",authorizations = { @Authorization(value="Bearer")},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = WithdrawFundResponseDto.class)
    public ResponseEntity<ApiResponse<WithdrawFundResponseDto>> withdrawFunds(@RequestBody @Valid final WithdrawFundRequestDto withdrawFundRequestDto) {
        log.info("controller withdrawFunds- for :: [{}]", withdrawFundRequestDto.getAmount() );
        WithdrawFundResponseDto response = transactionService.withdrawFunds(withdrawFundRequestDto);
        return ResponseEntity.ok(ApiResponse.buildSuccessTxn(response));
    }

    @PostMapping("/transfer-fund")
    @ApiOperation(value = "transfer funds",authorizations = { @Authorization(value="Bearer")},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = TransferResponseDto.class)
    public ResponseEntity<ApiResponse<TransferResponseDto>> transferFunds(@RequestBody @Valid final TransferFundRequestDto transferFundRequestDto) {
        log.info("controller transferFunds- for :: [{}]", transferFundRequestDto.getReceiverAccountNumber() );
        TransferResponseDto response = transactionService.transferFunds(transferFundRequestDto);
        return ResponseEntity.ok(ApiResponse.buildSuccessTxn(response));
    }

}
