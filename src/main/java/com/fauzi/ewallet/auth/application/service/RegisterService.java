package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.RegisterResult;
import com.fauzi.ewallet.auth.application.usecase.RegisterUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.model.Role;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.domain.repository.PasswordHasher;
import com.fauzi.ewallet.shared.exception.AlreadyExistsException;
import com.fauzi.ewallet.user.application.result.UserProfileResult;
import com.fauzi.ewallet.user.application.usecase.UserCommandUseCase;
import com.fauzi.ewallet.user.application.usecase.UserQueryUseCase;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegisterService implements RegisterUseCase{
    private final AuthRepository repository;
    private final PasswordHasher passwordHasher;
    private final UserQueryUseCase userQuery;
    private final UserCommandUseCase userCommand;

    public RegisterResult execute(RegisterCommand command){
        if (repository.findByEmail(command.email()).isPresent()){
            throw new AlreadyExistsException("Email is already in use");
        }
        if (userQuery.existByUsername(command.username())){
            throw new AlreadyExistsException("Username is already in use");
        }

        String password = passwordHasher.hash(command.password());
        UUID userId = UUID.randomUUID();
        Role role = Role.ROLE_USER;

        AuthUser authUser = new AuthUser(userId, command.email(), password, role);
        repository.save(authUser);

        UserProfileResult result = userCommand.createProfile(userId, command.name(), command.username(), command.phoneNumber());
        return new RegisterResult(result.name(), result.username(), result.phoneNumber(), authUser.getEmail());
    }
}
