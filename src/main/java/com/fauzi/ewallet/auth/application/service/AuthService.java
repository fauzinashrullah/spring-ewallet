package com.fauzi.ewallet.auth.application.service;

import java.time.Duration;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.command.LoginCommand;
import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.TokenResult;
import com.fauzi.ewallet.auth.application.result.UserDataResult;
import com.fauzi.ewallet.auth.application.usecase.AuthUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.model.Role;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.domain.repository.PasswordHasher;
import com.fauzi.ewallet.auth.domain.repository.RefreshTokenRepository;
import com.fauzi.ewallet.shared.exception.AlreadyExistsException;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.BlacklistTokenRepository;
import com.fauzi.ewallet.shared.security.JwtProvider;
import com.fauzi.ewallet.user.application.result.UserProfileResult;
import com.fauzi.ewallet.user.application.usecase.UserCommandUseCase;
import com.fauzi.ewallet.user.application.usecase.UserQueryUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase{
    
    private final JwtProvider jwt;
    private final AuthRepository repository;
    private final UserQueryUseCase userQuery;
    private final PasswordHasher passwordHasher;
    private final UserCommandUseCase userCommand;
    private final BlacklistTokenRepository blacklistRepo;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public UserDataResult register(RegisterCommand command){
        if (repository.findByEmail(command.email()).isPresent()){
            throw new AlreadyExistsException("Email is already in use");
        }
        if (userQuery.existByUsername(command.username())){
            throw new AlreadyExistsException("Username is already in use");
        }

        String password = passwordHasher.hash(command.password());
        UUID userId = UUID.randomUUID();
        Role role = Role.ROLE_USER;

        AuthUser authUser = new AuthUser(userId, command.email(), password, role);
        repository.save(authUser);

        UserProfileResult result = userCommand.createProfile(userId, command.name(), command.username(), command.phoneNumber());
        return new UserDataResult(result.name(), result.username(), result.phoneNumber(), authUser.getEmail());
    }

    @Override
    public TokenResult login(LoginCommand query){
        AuthUser authUser = repository.findByEmail(query.email())
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

        AuthUser authUser = repository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));
        String accessToken = jwt.generateToken(authUser.getId(), authUser.getEmail(), authUser.getRole().toString());
        String refreshToken = jwt.generateRefreshToken(userId);
        Duration ttl = jwt.getExpirationDuration(refreshToken);

        refreshTokenRepository.save(userId, refreshToken, jwt.getExpirationDuration(refreshToken));
        return new TokenResult(accessToken, refreshToken, ttl);
    }
}
