package com.fauzi.ewallet.auth.application.service;

import java.time.Duration;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.command.LoginCommand;
import com.fauzi.ewallet.auth.application.result.TokenResult;
import com.fauzi.ewallet.auth.application.usecase.AuthUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.domain.repository.PasswordHasher;
import com.fauzi.ewallet.auth.domain.repository.RefreshTokenRepository;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.BlacklistTokenRepository;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase{
    
    private final AuthRepository authRepository;
    private final PasswordHasher passwordHasher;
    private final JwtTokenProvider jwt;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BlacklistTokenRepository blacklistRepo;

    @Override
    public TokenResult login(LoginCommand query){
        AuthUser authUser = authRepository.findByEmail(query.email())
            .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!passwordHasher.verify(query.password(), authUser.getPassword()) && !authUser.isActive()){
            throw new UnauthorizedException("Invalid email or password");
        }
        
        String accessToken = jwt.generateToken(authUser.getId(), authUser.getEmail(), authUser.getRole().toString());
        String refreshToken = jwt.generateRefreshToken(authUser.getId());

        Duration ttl = jwt.getExpirationDuration(refreshToken);
        refreshTokenRepository.save(authUser.getId(), refreshToken, ttl);

        return new TokenResult(accessToken, refreshToken, ttl);
    }

    @Override
    public void logout(String authHeader) {
        String token = jwt.resolveToken(authHeader);
        if (blacklistRepo.isBlacklisted(token)){
            throw new UnauthorizedException("Invalid access token");
        }
        
        Duration ttl = jwt.getExpirationDuration(token);
        UUID userId = jwt.getUserIdFromToken(token);

        blacklistRepo.blacklist(token, ttl);
        refreshTokenRepository.delete(userId);
    }

    @Override
    public TokenResult refresh(String token){
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
