package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.command.LoginCommand;
import com.fauzi.ewallet.auth.application.result.TokenResult;

public interface LoginUseCase {
    TokenResult execute(LoginCommand command);
}
