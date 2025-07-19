package com.fauzi.ewallet.auth.application.usecase;

import java.util.UUID;

public interface UpdateEmailUseCase {
    String execute (UUID userId, String email);
}
