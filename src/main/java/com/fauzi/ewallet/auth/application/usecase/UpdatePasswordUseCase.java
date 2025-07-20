package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.application.command.UpdatePasswordCommand;

public interface UpdatePasswordUseCase {
    void execute (UpdatePasswordCommand command);
}
