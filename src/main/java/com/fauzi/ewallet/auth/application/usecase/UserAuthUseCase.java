package com.fauzi.ewallet.auth.application.usecase;

import java.util.List;
import java.util.UUID;

import com.fauzi.ewallet.auth.application.command.UpdatePasswordCommand;
import com.fauzi.ewallet.auth.application.result.GetAuthResult;
import com.fauzi.ewallet.auth.application.result.UserAuthResult;

public interface UserAuthUseCase {
    UserAuthResult getCurrent();
    String getAuthEmail (UUID id);
    void updateEmail (String email);
    void updatePassword (UpdatePasswordCommand command);
    void deleteUser(String authHeader);
    List<GetAuthResult> getAllAuth ();
}
