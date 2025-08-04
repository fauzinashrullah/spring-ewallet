package com.fauzi.ewallet.auth.application.outbound.usecase;

import java.util.List;
import java.util.UUID;

import com.fauzi.ewallet.auth.application.outbound.dto.result.GetAuthResult;

public interface GetAuthUseCase {
    List<GetAuthResult> getAllAuth ();
    String getAuthEmail (UUID id);
}
