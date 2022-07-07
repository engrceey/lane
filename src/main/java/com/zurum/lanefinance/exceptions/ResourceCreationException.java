package com.zurum.lanefinance.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class ResourceCreationException extends RuntimeException{
    protected String message;
    protected HttpStatus status;


    public ResourceCreationException(String message) {
        super(message);
        this.message = message;
    }

    public ResourceCreationException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}