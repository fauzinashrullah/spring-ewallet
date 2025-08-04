package com.fauzi.ewallet.auth.application.inbound.usecase;

import com.fauzi.ewallet.auth.application.inbound.dto.command.LoginCommand;
import com.fauzi.ewallet.auth.application.inbound.dto.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.inbound.dto.result.TokenResult;
import com.fauzi.ewallet.auth.application.inbound.dto.result.UserDataResult;

public interface AuthUseCase {
    UserDataResult register(RegisterCommand query);
    TokenResult login(LoginCommand command);
    void logout(String accessToken);
    TokenResult refresh(String token);
}
