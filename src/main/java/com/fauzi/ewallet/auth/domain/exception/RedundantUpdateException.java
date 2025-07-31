package com.fauzi.ewallet.auth.domain.exception;

import com.fauzi.ewallet.shared.exception.BusinessException;

public class RedundantUpdateException extends BusinessException {
    public RedundantUpdateException(String message) {
        super(message);
    }
}
