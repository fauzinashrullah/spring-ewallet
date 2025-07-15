package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.result.TokenResult;

public interface RefreshUseCase {
    TokenResult execute(String token);
}
