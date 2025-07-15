package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.UserResult;

public interface RegisterUseCase {
    UserResult execute(RegisterCommand query);
}
