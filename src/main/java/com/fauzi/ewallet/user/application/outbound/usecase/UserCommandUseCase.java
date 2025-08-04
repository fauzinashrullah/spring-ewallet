package com.fauzi.ewallet.user.application.outbound.usecase;

import java.util.UUID;

import com.fauzi.ewallet.user.application.outbound.dto.result.UserProfileResult;

public interface UserCommandUseCase {
    UserProfileResult createProfile(UUID authUserId, String fullname, String username, String phoneNumber);
}
