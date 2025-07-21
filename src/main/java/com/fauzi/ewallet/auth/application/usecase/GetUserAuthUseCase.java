package com.fauzi.ewallet.auth.application.usecase;

import java.util.UUID;

import com.fauzi.ewallet.auth.application.result.GetAuthResult;

public interface GetUserAuthUseCase {
    GetAuthResult execute (UUID id);
}
