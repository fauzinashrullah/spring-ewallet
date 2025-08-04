package com.fauzi.ewallet.auth.application.inbound.usecase;

import com.fauzi.ewallet.auth.application.inbound.dto.command.UpdatePasswordCommand;
import com.fauzi.ewallet.auth.application.inbound.dto.result.UserDataResult;

public interface UserAuthUseCase {
    UserDataResult getCurrent();
    void updateEmail (String email);
    void updatePassword (UpdatePasswordCommand command);
    void deleteUser(String authHeader);
}
