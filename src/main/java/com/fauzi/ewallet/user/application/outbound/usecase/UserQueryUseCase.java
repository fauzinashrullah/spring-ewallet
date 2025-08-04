package com.fauzi.ewallet.user.application.outbound.usecase;

import java.util.UUID;

import com.fauzi.ewallet.user.application.outbound.dto.result.UserSummaryResult;


public interface UserQueryUseCase {
    UserSummaryResult findByAuthUserId(UUID authUserId);
    boolean existByUsername(String username);
}
