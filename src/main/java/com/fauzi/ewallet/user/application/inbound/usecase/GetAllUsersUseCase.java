package com.fauzi.ewallet.user.application.inbound.usecase;

import java.util.List;

import com.fauzi.ewallet.user.application.inbound.dto.result.UserResult;

public interface GetAllUsersUseCase {
    List<UserResult> execute ();
}
