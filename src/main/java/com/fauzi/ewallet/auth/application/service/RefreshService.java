package com.fauzi.ewallet.auth.application.service;

import java.time.Duration;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.result.TokenResult;
import com.fauzi.ewallet.auth.application.usecase.RefreshUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.domain.repository.RefreshTokenRepository;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshService implements RefreshUseCase{

    private final JwtTokenProvider jwt;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthRepository authRepository;

    public TokenResult execute(String token){
        if(!jwt.validateToken(token)){
            throw new UnauthorizedException("Invalid refresh token");
        }

        UUID userId = jwt.getUserIdFromToken(token);
        if(!refreshTokenRepository.isValid(userId, token)){
            throw new UnauthorizedException("Refresh token revoked or not found");
        }

        refreshTokenRepository.delete(userId);

        AuthUser authUser = authRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));
        String accessToken = jwt.generateToken(authUser.getId(), authUser.getEmail(), authUser.getRole().toString());
        String refreshToken = jwt.generateRefreshToken(userId);
        Duration ttl = jwt.getExpirationDuration(refreshToken);

        refreshTokenRepository.save(userId, refreshToken, jwt.getExpirationDuration(refreshToken));
        return new TokenResult(accessToken, refreshToken, ttl);
    }
    
}
