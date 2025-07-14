package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.web.dto.LoginRequest;
import com.fauzi.ewallet.auth.web.dto.LoginResponse;

public interface LoginUseCase {
    LoginResponse execute(LoginRequest request);
}
