package com.fauzi.ewallet.shared.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}

