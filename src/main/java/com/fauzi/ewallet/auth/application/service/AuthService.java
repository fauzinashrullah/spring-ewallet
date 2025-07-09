package com.fauzi.ewallet.auth.application.service;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.dto.LoginRequest;
import com.fauzi.ewallet.auth.application.dto.LoginResponse;
import com.fauzi.ewallet.auth.application.dto.RegisterRequest;
import com.fauzi.ewallet.auth.application.usecase.AuthUseCase;
import com.fauzi.ewallet.auth.domain.repository.AuthRepositoryPort;
import com.fauzi.ewallet.auth.infrastructure.mapper.PasswordHasher;
import com.fauzi.ewallet.user.infrastructure.mapper.UserMapper;
import com.fauzi.ewallet.user.infrastructure.persistence.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final AuthRepositoryPort authRepositoryPort;
    private final PasswordHasher passwordHasher;

    public void register(RegisterRequest request){
        String password = passwordHasher.hash(request.getPassword());

        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(password);
        authRepositoryPort.save(UserMapper.toDomain(userEntity));
    }

    public LoginResponse login(LoginRequest request){
        return new LoginResponse(request.getEmail(), request.getPassword(),"token");
    }
}
