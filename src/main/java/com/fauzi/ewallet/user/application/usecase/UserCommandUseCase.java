package com.fauzi.ewallet.user.application.usecase;

import java.util.UUID;

import com.fauzi.ewallet.user.application.result.UserProfileResult;

public interface UserCommandUseCase {
    UserProfileResult createProfile(UUID authUserId, String fullname, String username, String phoneNumber);
}
