package com.zurum.lanefinance.service.implementations;

import com.zurum.lanefinance.constants.ResponseStatus;
import com.zurum.lanefinance.dtos.request.DepositAccountRequestDto;
import com.zurum.lanefinance.dtos.request.TransferFundRequestDto;
import com.zurum.lanefinance.dtos.request.WithdrawFundRequestDto;
import com.zurum.lanefinance.dtos.response.DepositResponseDto;
import com.zurum.lanefinance.dtos.response.FetchAccountResponseDto;
import com.zurum.lanefinance.dtos.response.TransferResponseDto;
import com.zurum.lanefinance.dtos.response.WithdrawFundResponseDto;
import com.zurum.lanefinance.entity.User;
import com.zurum.lanefinance.entity.Wallet;
import com.zurum.lanefinance.exceptions.CustomException;
import com.zurum.lanefinance.exceptions.InsufficientBalanceException;
import com.zurum.lanefinance.exceptions.ResourceNotFoundException;
import com.zurum.lanefinance.repository.WalletRepository;
import com.zurum.lanefinance.service.TransactionService;
import com.zurum.lanefinance.service.UserService;
import com.zurum.lanefinance.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final WalletRepository walletRepository;
    private final WalletService accountService;
    private final UserService userService;

    @Override
    @Transactional
    public DepositResponseDto depositFunds(DepositAccountRequestDto depositAccountRequestDto) {
        Wallet account = getAccount(depositAccountRequestDto.getReceiverAccountNumber());
        BigDecimal newBalance = account.getAccountBalance().add(depositAccountRequestDto.getAmount());
        log.info("Funding account of :: [{}] :: with :: [{}] ::", account.getAccountNumber(), newBalance);
        account.setAccountBalance(newBalance);
        walletRepository.save(account);
        return buildDepositResponse(depositAccountRequestDto);
    }

    private DepositResponseDto buildDepositResponse(DepositAccountRequestDto depositAccountRequestDto) {
        return DepositResponseDto
                .builder()
                .depositAccountNumber(depositAccountRequestDto.getReceiverAccountNumber())
                .isTransactionSuccessful(true)
                .receiverName(depositAccountRequestDto.getReceiver())
                .statusCode(ResponseStatus.SUCCESSFUL.getCode())
                .build();
    }

    @Override
    @Transactional
    public TransferResponseDto transferFunds(TransferFundRequestDto transferFundRequestDto) {
        log.info("Transaction service transferFunds");
        Wallet transferAccount = getAccountLogIn();
        Wallet receiverAccount = getAccount(transferFundRequestDto.getReceiverAccountNumber());

        if (isAccountEligibleForTransfer(transferAccount.getAccountNumber(), transferFundRequestDto.getAmount())) {
            checkKYCLevel(transferFundRequestDto.getAmount().doubleValue(), "transfer");
            BigDecimal newTransferredBalance = transferAccount.getAccountBalance().subtract(transferFundRequestDto.getAmount());
            BigDecimal newReceiverBalance = receiverAccount.getAccountBalance().add(transferFundRequestDto.getAmount());

            transferAccount.setAccountBalance(newTransferredBalance);
            walletRepository.save(transferAccount);

            receiverAccount.setAccountBalance(newReceiverBalance);
            walletRepository.save(receiverAccount);

            log.info("finished transfer, sending response");
            return buildTransferResponse(transferAccount,receiverAccount);
        }
        throw new InsufficientBalanceException("Insufficient balance to complete this transaction", HttpStatus.BAD_REQUEST);
    }

    private TransferResponseDto buildTransferResponse(Wallet sender,Wallet reciver) {
        return TransferResponseDto
                .builder()
                .sendAccountNumber(sender.getAccountNumber())
                .isTransactionSuccessful(true)
                .receiverName(reciver.getUser().getFirstName())
                .senderName(sender.getUser().getFirstName())
                .statusCode(ResponseStatus.SUCCESSFUL.getCode())
                .build();
    }

    @Override
    @Transactional
    public WithdrawFundResponseDto withdrawFunds(WithdrawFundRequestDto withdrawFundRequestDto) {
        log.info("Account Transaction service ");
        Wallet withdrawAccount = getAccountLogIn();

        if(isAccountEligibleForTransfer(withdrawAccount.getAccountNumber(), withdrawFundRequestDto.getAmount())) {
            checkKYCLevel(withdrawFundRequestDto.getAmount().doubleValue(), "deposit");
            BigDecimal newWithdrawAmount = withdrawAccount.getAccountBalance().subtract(withdrawFundRequestDto.getAmount());
            withdrawAccount.setAccountBalance(newWithdrawAmount);
            walletRepository.save(withdrawAccount);
            log.info("finished withdraw for :: [{}]", withdrawAccount.getAccountNumber());
            return buildWithDrawResponse(withdrawAccount);
        }
        throw new InsufficientBalanceException("Insufficient balance to complete this transaction", HttpStatus.BAD_REQUEST);
    }

    private WithdrawFundResponseDto buildWithDrawResponse(Wallet withdrawAccount) {
        return WithdrawFundResponseDto
                .builder()
                .accountNum(withdrawAccount.getAccountNumber())
                .isSuccessful(true)
                .status(ResponseStatus.SUCCESSFUL.getCode())
                .name(withdrawAccount.getUser().getFirstName())
                .build();
    }

    private boolean isAccountEligibleForTransfer(long account, BigDecimal amount) {
        FetchAccountResponseDto accountDetails = accountService.fetchAccount(account);
        return accountDetails.getAccountBalance().compareTo(amount) >= 0;
    }

    private Wallet getAccount(long accountNumber) {
        return walletRepository.getAccountByAccountNumber(accountNumber).orElseThrow(
                () -> {throw new ResourceNotFoundException("account not found");
                }
        );
    }

    private void checkKYCLevel(double amount, String operation){
        User loggedInUser = userService.getLoggedInUser();
        double maxAllowableAmount = loggedInUser.getKycLevel().getTrnMaxLimit();
        if (amount > maxAllowableAmount) {
            throw new CustomException(
                    "You cannot "+operation+" "+amount+" try a figure below"+
                            maxAllowableAmount+" or apply to increase your KYC Level");
        }

    }



    private Wallet getAccountLogIn(){
        return walletRepository.
                getAccountByAccountNumber(accountService.getLoggedInUserAccountDetails().getAccountNumber()).get();
    }
}
