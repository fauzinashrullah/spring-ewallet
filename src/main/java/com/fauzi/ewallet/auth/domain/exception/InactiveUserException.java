package com.fauzi.ewallet.auth.domain.exception;

import com.fauzi.ewallet.shared.exception.BusinessException;

public class InactiveUserException extends BusinessException {
    public InactiveUserException (String message){
        super(message);
    }
}
