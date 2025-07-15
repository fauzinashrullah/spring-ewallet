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
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;
import com.fauzi.ewallet.user.application.usecase.UserQueryService;
import com.fauzi.ewallet.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase{
    private final AuthRepository authRepository;
    private final PasswordHasher passwordHasher;
    private final UserQueryService userQueryService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResult execute(LoginCommand query){
        AuthUser authUser = authRepository.findByEmail(query.email())
            .orElseThrow(() -> new NotFoundException("email not found"));

        if (!passwordHasher.verify(query.password(), authUser.getPassword())){
            throw new UnauthorizedException("Password not match");
        }
        User user = userQueryService.findByAuthUserId(authUser.getId());

        String accessToken = jwtTokenProvider.generateToken(authUser.getId(), authUser.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAuthUserId());

        Duration ttl = jwtTokenProvider.getExpiration(refreshToken);
        refreshTokenRepository.save(authUser.getId(), refreshToken, ttl);

        return new TokenResult(accessToken, refreshToken, ttl);
    }
}
