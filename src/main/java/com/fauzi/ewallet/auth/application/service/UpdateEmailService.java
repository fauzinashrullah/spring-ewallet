package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.UpdateEmailUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.shared.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateEmailService implements UpdateEmailUseCase {
    
    private final AuthRepository authRepository;

    public String execute (UUID userId, String email){
        AuthUser authUser = authRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));
        authUser.updateEmail(email);
        authRepository.save(authUser);
        return authUser.getEmail();
    }
}
