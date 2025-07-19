package com.fauzi.ewallet.user.application.usecase;

import com.fauzi.ewallet.user.application.command.UpdateCommand;
import com.fauzi.ewallet.user.application.result.UserResult;

public interface UpdateUserUseCase {
    UserResult execute (UpdateCommand command);
}
