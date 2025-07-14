package com.fauzi.ewallet.auth.application.usecase;

import com.fauzi.ewallet.auth.web.dto.RegisterRequest;

public interface RegisterUseCase {
    void execute(RegisterRequest request);
}
