package com.fauzi.ewallet.auth.domain.repository;

import java.time.Duration;

public interface BlacklistTokenRepository {
    void blacklist(String token, Duration ttl);
    boolean isBlacklisted(String token);
}

