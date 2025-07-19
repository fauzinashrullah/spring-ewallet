package com.fauzi.ewallet.shared.security;

import java.time.Duration;

public interface BlacklistTokenRepository {
    void blacklist(String token, Duration ttl);
    boolean isBlacklisted(String token);
}

