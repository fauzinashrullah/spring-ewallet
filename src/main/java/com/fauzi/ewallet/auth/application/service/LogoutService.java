package com.fauzi.ewallet.auth.application.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.LogoutUseCase;
import com.fauzi.ewallet.auth.domain.repository.BlacklistTokenRepository;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {

    private final BlacklistTokenRepository blacklistRepo;
    private final JwtTokenProvider jwt;

    @Override
    public void execute(String token) {
        Instant exp = jwt.getExpiration(token);
        Duration ttl = Duration.between(Instant.now(), exp);
        blacklistRepo.blacklist(token, ttl);
    }
}
