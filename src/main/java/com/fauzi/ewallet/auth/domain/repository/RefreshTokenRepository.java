package com.fauzi.ewallet.auth.domain.repository;

import java.time.Duration;
import java.util.UUID;

public interface RefreshTokenRepository {
    void save(UUID userId, String refreshToken, Duration ttl);
    boolean isValid(UUID userId, String refreshToken);
    void delete(UUID userId);
}

