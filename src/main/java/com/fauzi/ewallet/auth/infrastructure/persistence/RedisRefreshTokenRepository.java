package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.time.Duration;
import java.util.UUID;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.fauzi.ewallet.auth.domain.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisRefreshTokenRepository implements RefreshTokenRepository {

    private final StringRedisTemplate redis;

    private String key(UUID userId) {
        return "refresh:" + userId;
    }

    @Override
    public void save(UUID userId, String refreshToken, Duration ttl) {
        redis.opsForValue().set(key(userId), refreshToken, ttl);
    }

    @Override
    public boolean isValid(UUID userId, String refreshToken) {
        String stored = redis.opsForValue().get(key(userId));
        return stored != null && stored.equals(refreshToken);
    }

    @Override
    public void delete(UUID userId) {
        redis.delete(key(userId));
    }
}
