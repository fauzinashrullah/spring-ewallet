package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.web.dto.CurrentUserResponse;

public interface GetCurrentUserUseCase {
    CurrentUserResponse execute (String token);
}
