package com.fauzi.ewallet.auth.domain.exception;

import com.fauzi.ewallet.shared.exception.BusinessException;

public class UserActiveException extends BusinessException {
    public UserActiveException (String message){
        super(message);
    }
}
