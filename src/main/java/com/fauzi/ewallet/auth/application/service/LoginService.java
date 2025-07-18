package com.fauzi.ewallet.auth.application.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.command.LoginCommand;
import com.fauzi.ewallet.auth.application.result.TokenResult;
import com.fauzi.ewallet.auth.application.usecase.LoginUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.domain.repository.PasswordHasher;
import com.fauzi.ewallet.auth.domain.repository.RefreshTokenRepository;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase{
    private final AuthRepository authRepository;
    private final PasswordHasher passwordHasher;
    private final JwtTokenProvider jwt;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResult execute(LoginCommand query){
        AuthUser authUser = authRepository.findByEmail(query.email())
            .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!passwordHasher.verify(query.password(), authUser.getPassword())){
            throw new UnauthorizedException("Invalid email or password");
        }
        
        String accessToken = jwt.generateToken(authUser.getId(), authUser.getEmail(), authUser.getRole().toString());

        String refreshToken = jwt.generateRefreshToken(authUser.getId());

        Duration ttl = jwt.getExpirationDuration(refreshToken);
        refreshTokenRepository.save(authUser.getId(), refreshToken, ttl);

        return new TokenResult(accessToken, refreshToken, ttl);
    }
}
