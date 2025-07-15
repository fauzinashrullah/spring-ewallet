package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.UserResult;
import com.fauzi.ewallet.auth.application.usecase.RegisterUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.domain.repository.PasswordHasher;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.user.application.service.UserCommandServiceImpl;
import com.fauzi.ewallet.user.application.usecase.UserQueryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegisterService implements RegisterUseCase{
    private final AuthRepository authRepository;
    private final PasswordHasher passwordHasher;
    private final UserQueryService userQueryService;
    private final UserCommandServiceImpl userCommandServiceImpl;

    public UserResult execute(RegisterCommand query){
        if (authRepository.findByEmail(query.email()).isPresent()){
            throw new NotFoundException("User not found");
        }

        String password = passwordHasher.hash(query.password());
        UUID userId = UUID.randomUUID();

        AuthUser authUser = new AuthUser(userId, query.email(), password);
        authRepository.save(authUser);

        userCommandServiceImpl.createProfile(userId, query.name());
        String name = userQueryService.findByAuthUserId(userId).getFullName();
        return new UserResult(name, authUser.getEmail());
    }
}
