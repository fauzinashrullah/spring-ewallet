package com.fauzi.ewallet.auth.domain.repository;

import java.time.Duration;

public interface RefreshTokenRepository {
    void save(String userId, String refreshToken, Duration ttl);
    boolean isValid(String userId, String refreshToken);
    void delete(String userId);
}

