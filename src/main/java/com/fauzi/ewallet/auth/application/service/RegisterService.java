package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.RegisterUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.infrastructure.mapper.PasswordHasher;
import com.fauzi.ewallet.auth.web.dto.RegisterRequest;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.user.application.service.UserCommandServiceImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegisterService implements RegisterUseCase{
    private final AuthRepository authRepository;
    private final PasswordHasher passwordHasher;
    private final UserCommandServiceImpl userCommandServiceImpl;

    public void execute(RegisterRequest request){
        if (authRepository.findByEmail(request.getEmail()).isPresent()){
            throw new NotFoundException("User not found");
        }

        String password = passwordHasher.hash(request.getPassword());
        UUID userId = UUID.randomUUID();

        AuthUser authUser = new AuthUser(userId, request.getEmail(), password);
        authRepository.save(authUser);

        userCommandServiceImpl.createProfile(userId, request.getName());
    }
}
