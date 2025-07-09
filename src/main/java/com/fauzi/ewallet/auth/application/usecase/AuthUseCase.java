package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.dto.LoginRequest;
import com.fauzi.ewallet.auth.application.dto.LoginResponse;
import com.fauzi.ewallet.auth.application.dto.RegisterRequest;

public interface AuthUseCase {
    void register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
