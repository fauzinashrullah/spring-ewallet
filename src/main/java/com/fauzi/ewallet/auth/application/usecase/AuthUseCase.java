package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.command.LoginCommand;
import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.TokenResult;
import com.fauzi.ewallet.auth.application.result.UserDataResult;

public interface AuthUseCase {
    UserDataResult register(RegisterCommand query);
    TokenResult login(LoginCommand command);
    void logout(String accessToken);
    TokenResult refresh(String token);
}
