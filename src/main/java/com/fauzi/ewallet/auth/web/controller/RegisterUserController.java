package com.fauzi.ewallet.auth.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.usecase.RegisterUseCase;
import com.fauzi.ewallet.auth.web.dto.request.RegisterRequest;
import com.fauzi.ewallet.auth.web.dto.response.UserResponse;
import com.fauzi.ewallet.auth.web.mapper.ApiMapper;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RegisterUserController {
    
    private final RegisterUseCase register;
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request) {
        RegisterCommand command = new RegisterCommand(
            request.getName(), 
            request.getEmail(), 
            request.getPassword()
            );

        UserResponse userResponse = ApiMapper.from(register.execute(command));
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ApiResponse<>(true, "Register success", userResponse));
    }
}
