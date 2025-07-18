package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.result.UserAuthResult;
import com.fauzi.ewallet.auth.application.usecase.GetCurrentUserUseCase;
import com.fauzi.ewallet.auth.domain.repository.BlacklistTokenRepository;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.application.usecase.UserQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetCurrentUserService implements GetCurrentUserUseCase{
    
    private final JwtTokenProvider jwt;
    private final UserQueryService userQueryService;
    private final BlacklistTokenRepository blacklistTokenRepository;
    
    public UserAuthResult execute (String authHeader){
        String token = jwt.resolveToken(authHeader);
        if (blacklistTokenRepository.isBlacklisted(token)){
            throw new UnauthorizedException("Token has been blacklisted");
        }
        if(!jwt.validateToken(token)){
            throw new UnauthorizedException("Invalid token");
        }
        UUID userId = jwt.getUserIdFromToken(token);
        String email = jwt.getEmailFromToken(token);

        UserResult user = userQueryService.findByAuthUserId(userId);

        UserAuthResult response = new UserAuthResult(user.fullName(), email);
        return response;
    }
}
