package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.result.UserAuthResult;

public interface GetCurrentUserUseCase {
    UserAuthResult execute ();
}
