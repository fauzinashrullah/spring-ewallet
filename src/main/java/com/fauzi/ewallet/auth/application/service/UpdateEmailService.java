package com.fauzi.ewallet.auth.application.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.UpdateEmailUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateEmailService implements UpdateEmailUseCase {
    
    private final AuthRepository authRepository;

    public void execute (String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        AuthUser authUser = authRepository.findById(userDetails.getId())
            .orElseThrow(() -> new NotFoundException("User not found"));
        authUser.updateEmail(email);
        authRepository.save(authUser);
    }
}
