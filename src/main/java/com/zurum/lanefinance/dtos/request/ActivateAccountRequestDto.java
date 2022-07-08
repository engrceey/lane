package com.zurum.lanefinance.dtos.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class ActivateAccountRequestDto {
    @Size(message = "Pin should be four digits", min = 4, max = 4)
    private String pin;

}
