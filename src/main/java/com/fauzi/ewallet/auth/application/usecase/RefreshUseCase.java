package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.dto.TokenResponse;

public interface RefreshUseCase {
    TokenResponse execute(String token);
}
