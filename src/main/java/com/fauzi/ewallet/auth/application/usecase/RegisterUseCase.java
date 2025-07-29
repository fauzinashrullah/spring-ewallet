package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.RegisterResult;

public interface RegisterUseCase {
    RegisterResult execute(RegisterCommand query);
}
