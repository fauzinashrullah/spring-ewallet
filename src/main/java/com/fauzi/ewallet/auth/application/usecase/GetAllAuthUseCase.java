package com.fauzi.ewallet.auth.application.usecase;

import java.util.List;

import com.fauzi.ewallet.auth.application.result.GetAuthResult;

public interface GetAllAuthUseCase {
    List<GetAuthResult> execute ();
}
