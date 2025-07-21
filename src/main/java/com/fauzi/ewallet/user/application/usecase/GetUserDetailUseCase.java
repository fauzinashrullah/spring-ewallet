package com.fauzi.ewallet.user.application.usecase;

import java.util.UUID;

import com.fauzi.ewallet.user.application.result.UserResult;

public interface GetUserDetailUseCase {
    UserResult execute (UUID id);
}
