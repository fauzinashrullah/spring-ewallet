package com.fauzi.ewallet.user.application.usecase;

import java.util.UUID;

import com.fauzi.ewallet.user.domain.model.User;

public interface UserQueryService {
    User findByAuthUserId(UUID authUserId);
}
