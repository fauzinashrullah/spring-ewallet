package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.fauzi.ewallet.auth.domain.repository.BlacklistTokenRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisBlacklistTokenRepository implements BlacklistTokenRepository {

    private final StringRedisTemplate redisTemplate;

    private String key(String token){
        return "blacklist:" + token;
    }

    @Override
    public void blacklist(String token, Duration ttl) {
        redisTemplate.opsForValue().set(key(token), "", ttl);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return redisTemplate.hasKey(key(token));
    }
}