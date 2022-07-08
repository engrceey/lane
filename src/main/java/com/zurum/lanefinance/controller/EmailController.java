package com.zurum.lanefinance.controller;

import com.zurum.lanefinance.dtos.request.EmailDto;
import com.zurum.lanefinance.dtos.response.ApiResponse;
import com.zurum.lanefinance.service.SendMailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("mail")
public class EmailController {

    private final SendMailService sendMailService;

    @PostMapping()
    @ApiOperation(value = "send email",authorizations = { @Authorization(value="Bearer")},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = Boolean.class)
    public ResponseEntity<ApiResponse<Boolean>> sendMail(@RequestBody @Valid final EmailDto emailDto) {
        log.info("email controller -: sending email to [{}]", emailDto.getRecipient());
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> sendMailService.sendEmail(emailDto));

        return
                future.isDone() ?
                        ResponseEntity.ok(ApiResponse.<Boolean>builder()
                                .isSuccessful(true)
                                .statusMessage("email sent successfully")
                                .data(true)
                                .build()
                        )
                        :
                        ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ApiResponse.<Boolean>builder()
                                .isSuccessful(false)
                                .statusMessage("email Not successful")
                                .data(false)
                                .build()
                        );

    }
}
