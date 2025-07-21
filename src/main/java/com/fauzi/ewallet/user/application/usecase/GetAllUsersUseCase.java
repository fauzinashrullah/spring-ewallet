package com.fauzi.ewallet.user.application.usecase;

import java.util.List;

import com.fauzi.ewallet.user.application.result.UserResult;

public interface GetAllUsersUseCase {
    List<UserResult> execute ();
}
