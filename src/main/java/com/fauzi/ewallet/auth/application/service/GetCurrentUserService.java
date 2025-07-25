package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.result.UserAuthResult;
import com.fauzi.ewallet.auth.application.usecase.GetCurrentUserUseCase;
import com.fauzi.ewallet.shared.security.UserDetailsImpl;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.application.usecase.UserQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetCurrentUserService implements GetCurrentUserUseCase{
    
    private final UserQueryService userQueryService;
    
    public UserAuthResult execute (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UUID userId = userDetails.getId();
        UserResult user = userQueryService.findByAuthUserId(userId);

        UserAuthResult response = new UserAuthResult(user.fullname(), userDetails.getEmail());
        return response;
    }
}
