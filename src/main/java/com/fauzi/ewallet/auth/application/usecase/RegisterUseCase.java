package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.UserDataResult;

public interface RegisterUseCase {
    UserDataResult execute(RegisterCommand query);
}
