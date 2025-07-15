package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.result.UserResult;

public interface GetCurrentUserUseCase {
    UserResult execute (String token);
}
