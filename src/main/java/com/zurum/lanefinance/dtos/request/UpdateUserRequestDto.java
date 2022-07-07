package com.zurum.lanefinance.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateUserRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
