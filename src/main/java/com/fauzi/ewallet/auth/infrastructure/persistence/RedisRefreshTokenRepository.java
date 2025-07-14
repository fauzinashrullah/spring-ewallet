package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.fauzi.ewallet.auth.domain.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisRefreshTokenRepository implements RefreshTokenRepository {

    private final StringRedisTemplate redis;

    private String key(String userId) {
        return "refresh:" + userId;
    }

    @Override
    public void save(String userId, String refreshToken, Duration ttl) {
        redis.opsForValue().set(key(userId), refreshToken, ttl);
    }

    @Override
    public boolean isValid(String userId, String refreshToken) {
        String stored = redis.opsForValue().get(key(userId));
        return stored != null && stored.equals(refreshToken);
    }

    @Override
    public void delete(String userId) {
        redis.delete(key(userId));
    }
}
