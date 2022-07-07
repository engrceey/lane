package com.zurum.lanefinance.controller;

import com.zurum.lanefinance.dtos.request.EmailDto;
import com.zurum.lanefinance.dtos.response.ApiResponse;
import com.zurum.lanefinance.service.SendMailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("mail")
public class EmailController {

    private final SendMailService sendMailService;


    @PostMapping
    public ResponseEntity<ApiResponse<Boolean>> sendMail(@RequestBody @Valid final EmailDto emailDto) {
        sendMailService.sendEmail(emailDto);
        return ResponseEntity.ok(ApiResponse.<Boolean>builder()
                .isSuccessful(true)
                .statusMessage("email sent successfully")
                .data(true)
                .build()
        );

    }
}
