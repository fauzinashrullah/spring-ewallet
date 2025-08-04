package com.fauzi.ewallet.user.application.inbound.usecase;

import java.util.UUID;

import com.fauzi.ewallet.user.application.inbound.dto.result.UserResult;

public interface GetUserDetailUseCase {
    UserResult execute (UUID id);
}
