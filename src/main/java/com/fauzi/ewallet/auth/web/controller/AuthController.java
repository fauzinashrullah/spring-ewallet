package com.fauzi.ewallet.auth.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.usecase.GetCurrentUserUseCase;
import com.fauzi.ewallet.auth.application.usecase.LoginUseCase;
import com.fauzi.ewallet.auth.application.usecase.LogoutUseCase;
import com.fauzi.ewallet.auth.application.usecase.RegisterUseCase;
import com.fauzi.ewallet.auth.web.dto.CurrentUserResponse;
import com.fauzi.ewallet.auth.web.dto.LoginRequest;
import com.fauzi.ewallet.auth.web.dto.LoginResponse;
import com.fauzi.ewallet.auth.web.dto.RegisterRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUseCase register;
    private final LoginUseCase login;
    private final LogoutUseCase logout;
    private final GetCurrentUserUseCase getCurrentUser;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        register.execute(request);
        return ResponseEntity.ok("ok");
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = login.execute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        logout.execute(token);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/me")
    public ResponseEntity<?> me(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        CurrentUserResponse response = getCurrentUser.execute(token);
        return ResponseEntity.ok(response);
    }
    
    
    
}
