package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.DeleteUserUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase{

    private final AuthRepository repository;
    private final LogoutService logoutService;

    public void execute (String authHeader){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        AuthUser authUser =  repository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));
        if (!authUser.isActive()){
            throw new NotFoundException("User not found");
        }
        authUser.deactivate();
        
        repository.save(authUser);
        logoutService.execute(authHeader);
    }
}
