package com.fauzi.ewallet.user.application.inbound.usecase;

import com.fauzi.ewallet.user.application.inbound.dto.command.UpdateCommand;
import com.fauzi.ewallet.user.application.inbound.dto.result.UpdateUserResult;

public interface UpdateUserUseCase {
    UpdateUserResult execute (UpdateCommand command);
}
