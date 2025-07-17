package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.result.UserResult;
import com.fauzi.ewallet.auth.application.usecase.GetCurrentUserUseCase;
import com.fauzi.ewallet.auth.domain.repository.BlacklistTokenRepository;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;
import com.fauzi.ewallet.user.application.usecase.UserQueryService;
import com.fauzi.ewallet.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetCurrentUserService implements GetCurrentUserUseCase{
    
    private final JwtTokenProvider jwt;
    private final UserQueryService userQueryService;
    private final BlacklistTokenRepository blacklistTokenRepository;
    
    public UserResult execute (String authHeader){
        String token = jwt.resolveToken(authHeader);
        if (blacklistTokenRepository.isBlacklisted(token)){
            throw new UnauthorizedException("Token has been blacklisted");
        }
        if(!jwt.validateToken(token)){
            throw new UnauthorizedException("Invalid token");
        }
        UUID userId = jwt.getUserIdFromToken(token);
        String email = jwt.getEmailFromToken(token);

        User user = userQueryService.findByAuthUserId(userId);

        UserResult response = new UserResult(user.getFullName(), email);
        return response;
    }
}
