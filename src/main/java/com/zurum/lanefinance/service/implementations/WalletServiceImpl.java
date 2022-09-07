package com.zurum.lanefinance.service.implementations;

import com.zurum.lanefinance.dtos.request.ActivateAccountRequestDto;
import com.zurum.lanefinance.dtos.response.FetchAccountResponseDto;
import com.zurum.lanefinance.entity.AppUser;
import com.zurum.lanefinance.entity.Wallet;
import com.zurum.lanefinance.exceptions.ResourceNotFoundException;
import com.zurum.lanefinance.repository.UserRepository;
import com.zurum.lanefinance.repository.WalletRepository;
import com.zurum.lanefinance.service.WalletService;
import com.zurum.lanefinance.utils.AppUtil;
import com.zurum.lanefinance.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public FetchAccountResponseDto fetchAccount(long accountNumber) {

        Wallet account = accountRepository.getAccountByAccountNumber(accountNumber)
                .orElseThrow(
                        () -> {throw new ResourceNotFoundException("account not found");
                        }
                );
        return ModelMapperUtils.map(account,new FetchAccountResponseDto());
    }

    @Override
    public boolean activateAccount(ActivateAccountRequestDto activateAccountRequestDto) {
        Wallet loggedInUser = getLoggedInUserAccountDetails();
        loggedInUser.setActivated(true);
        loggedInUser.setPin(passwordEncoder.encode(activateAccountRequestDto.getPin()));
        accountRepository.save(loggedInUser);
        return true;
    }

    private boolean validateBalance(long receiverAccountNumber, BigDecimal amount) {
        Wallet account = accountRepository.getAccountByAccountNumber(receiverAccountNumber)
                .orElseThrow(
                        () -> {throw new ResourceNotFoundException("account number not found");
                        }
                );
        return account.getAccountBalance().compareTo(amount) >= 0;
    }

    public Wallet getLoggedInUserAccountDetails() {
        log.info("AccountServiceImpl getLoggedInUserAccountDetails- :: ");
        String loggedInUser = AppUtil.getPrincipal();
        log.info("AccountServiceImpl getLoggedInUserAccountDetails- logged In user :: [{}]", loggedInUser);
        AppUser user =  userRepository.getUserByEmail(loggedInUser).orElseThrow(
                () -> {throw new ResourceNotFoundException("user not found");
                }
        );
        return accountRepository.findById(user.getId()).orElseThrow(
                () -> {throw new ResourceNotFoundException("account not found");
                }
        );
    }
}
