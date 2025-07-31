package com.fauzi.ewallet.shared.exception;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatus();
}

