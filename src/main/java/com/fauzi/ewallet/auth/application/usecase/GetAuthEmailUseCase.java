package com.fauzi.ewallet.auth.application.usecase;

import java.util.UUID;

public interface GetAuthEmailUseCase {
    String execute (UUID id);
}
