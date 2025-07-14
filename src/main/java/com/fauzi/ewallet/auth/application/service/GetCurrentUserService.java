package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.GetCurrentUserUseCase;
import com.fauzi.ewallet.auth.domain.repository.BlacklistTokenRepository;
import com.fauzi.ewallet.auth.web.dto.CurrentUserResponse;
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
    
    public CurrentUserResponse execute (String token){
        if (blacklistTokenRepository.isBlacklisted(token)){
            throw new UnauthorizedException("Token has been blacklisted");
        }
        UUID userId = jwt.getUserIdFromToken(token);
        String email = jwt.getEmailFromToken(token);

        User user = userQueryService.findByAuthUserId(userId);

        return new CurrentUserResponse(user.getFullName(), email);
    }
}
