package com.fauzi.ewallet.user.application.usecase;

import java.util.UUID;

import com.fauzi.ewallet.user.application.result.UserResult;


public interface UserQueryService {
    UserResult findByAuthUserId(UUID authUserId);
}
