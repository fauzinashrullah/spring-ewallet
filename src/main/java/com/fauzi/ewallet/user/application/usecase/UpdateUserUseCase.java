package com.fauzi.ewallet.user.application.usecase;

import com.fauzi.ewallet.user.application.command.UpdateCommand;
import com.fauzi.ewallet.user.application.result.UpdateUserResult;

public interface UpdateUserUseCase {
    UpdateUserResult execute (UpdateCommand command);
}
