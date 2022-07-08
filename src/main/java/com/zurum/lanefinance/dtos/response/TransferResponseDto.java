package com.zurum.lanefinance.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferResponseDto {
    private String statusCode;
    private String senderName;
    private long sendAccountNumber;
    private String receiverName;
    private boolean isTransactionSuccessful;
}
