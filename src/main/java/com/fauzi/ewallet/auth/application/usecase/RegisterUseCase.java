package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.UserAuthResult;

public interface RegisterUseCase {
    UserAuthResult execute(RegisterCommand query);
}
