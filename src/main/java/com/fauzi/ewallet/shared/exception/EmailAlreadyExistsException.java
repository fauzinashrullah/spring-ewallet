package com.fauzi.ewallet.shared.exception;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException() {
        super("Email is already in use");
    }
}
