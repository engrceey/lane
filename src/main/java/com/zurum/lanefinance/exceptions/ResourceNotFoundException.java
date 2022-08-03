package com.zurum.lanefinance.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResourceNotFoundException extends RuntimeException {
    protected String message;

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}