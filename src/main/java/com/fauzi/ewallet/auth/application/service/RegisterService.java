package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.UserAuthResult;
import com.fauzi.ewallet.auth.application.usecase.RegisterUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.model.Role;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.domain.repository.PasswordHasher;
import com.fauzi.ewallet.shared.exception.EmailAlreadyExistsException;
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

    public UserAuthResult execute(RegisterCommand command){
        if (authRepository.findByEmail(command.email()).isPresent()){
            throw new EmailAlreadyExistsException();
        }

        String password = passwordHasher.hash(command.password());
        UUID userId = UUID.randomUUID();

        Role role = Role.ROLE_USER;
        AuthUser authUser = new AuthUser(userId, command.email(), password, role);
        authRepository.save(authUser);

        userCommandServiceImpl.createProfile(userId, command.name(), command.phoneNumber());
        String name = userQueryService.findByAuthUserId(userId).fullName();
        return new UserAuthResult(name, authUser.getEmail());
    }
}
