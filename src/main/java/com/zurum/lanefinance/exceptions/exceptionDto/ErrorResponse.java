package com.zurum.lanefinance.exceptions.exceptionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Date timestamp;
    private String message;
    private String error;
    private String[] errors;

    public ErrorResponse(Date date, String message, String error) {
        this.timestamp = date;
        this.message = message;
        this.error = error;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(Date date, String message, String[] errors) {
        this.timestamp = date;
        this.message = message;
        this.errors = errors;
    }
}