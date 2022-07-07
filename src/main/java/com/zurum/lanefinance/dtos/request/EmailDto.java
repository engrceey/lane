package com.zurum.lanefinance.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto {

    @NotBlank
    private String subject;

    @NotBlank
    @Email
    private String recipient;

    @NotBlank
    private String body;

    @NotBlank
    private String sender;

    private String attachment;
    private String cc;
    private String bcc;


}