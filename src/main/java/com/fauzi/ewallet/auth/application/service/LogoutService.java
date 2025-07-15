package com.fauzi.ewallet.auth.application.service;

import java.time.Duration;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.LogoutUseCase;
import com.fauzi.ewallet.auth.domain.repository.BlacklistTokenRepository;
import com.fauzi.ewallet.auth.domain.repository.RefreshTokenRepository;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {

    private final BlacklistTokenRepository blacklistRepo;
    private final JwtTokenProvider jwt;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void execute(String token) {
        Duration ttl = jwt.getExpiration(token);
        blacklistRepo.blacklist(token, ttl);
        UUID userId = jwt.getUserIdFromToken(token);
        refreshTokenRepository.delete(userId);
    }
}
