package com.zurum.lanefinance.dtos.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public  class DepositAccountRequestDto {

    @NotNull(message = "account cannot be empty")
    @Min(value = 0)
    private BigDecimal amount;

    @NotBlank(message = "sender cannot be blank")
    private String sender;

    @NotBlank(message = "receiver cannot be blank")
    private String receiver;

    @NotNull
    private Long receiverAccountNumber;

}
