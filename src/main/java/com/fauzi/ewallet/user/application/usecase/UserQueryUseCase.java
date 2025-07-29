package com.fauzi.ewallet.user.application.usecase;

import java.util.UUID;

import com.fauzi.ewallet.user.application.result.UserResult;


public interface UserQueryUseCase {
    UserResult findByAuthUserId(UUID authUserId);
    boolean existByUsername(String username);
}
