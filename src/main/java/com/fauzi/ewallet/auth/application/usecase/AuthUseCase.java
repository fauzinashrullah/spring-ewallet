package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.command.LoginCommand;
import com.fauzi.ewallet.auth.application.result.TokenResult;

public interface AuthUseCase {
    TokenResult login(LoginCommand command);
    void logout(String accessToken);
    TokenResult refresh(String token);
}
