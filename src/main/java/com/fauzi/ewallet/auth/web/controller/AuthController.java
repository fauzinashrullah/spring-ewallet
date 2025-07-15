package com.fauzi.ewallet.auth.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.command.LoginCommand;
import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.result.TokenResult;
import com.fauzi.ewallet.auth.application.usecase.GetCurrentUserUseCase;
import com.fauzi.ewallet.auth.application.usecase.LoginUseCase;
import com.fauzi.ewallet.auth.application.usecase.LogoutUseCase;
import com.fauzi.ewallet.auth.application.usecase.RefreshUseCase;
import com.fauzi.ewallet.auth.application.usecase.RegisterUseCase;
import com.fauzi.ewallet.auth.web.dto.request.LoginRequest;
import com.fauzi.ewallet.auth.web.dto.request.RegisterRequest;
import com.fauzi.ewallet.auth.web.dto.response.ApiResponse;
import com.fauzi.ewallet.auth.web.dto.response.TokenResponse;
import com.fauzi.ewallet.auth.web.dto.response.UserResponse;
import com.fauzi.ewallet.auth.web.mappper.ApiMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
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
    private final RefreshUseCase refresh;

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
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginCommand command = new LoginCommand(request.getEmail(), request.getPassword());
        TokenResult token = login.execute(command);
        ResponseCookie cookie = ResponseCookie.from("refresh_token", token.refreshToken())
            .httpOnly(true)
            .secure(true)
            .path("/api/v1/auth/refresh")
            .sameSite("Strict")
            .maxAge(token.ttl())
            .build();

        TokenResponse response = new TokenResponse(token.accessToken());
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new ApiResponse<>(true, "Login success", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@RequestHeader("Authorization") String authHeader) {
        logout.execute(authHeader);
        return ResponseEntity.ok(new ApiResponse<>(true, "Logout success", Map.of()));
    }

    @PostMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> me(@RequestHeader("Authorization") String authHeader) {
        UserResponse response = ApiMapper.from(getCurrentUser.execute(authHeader));
        return ResponseEntity.ok(new ApiResponse<>(true, "Get user success", response));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenResponse>> refresh(@CookieValue("refresh_token") String refreshToken) {
        TokenResult token = refresh.execute(refreshToken);
        ResponseCookie cookie = ResponseCookie.from("refresh_token", token.refreshToken())
            .httpOnly(true)
            .secure(true)
            .path("/api/v1/auth/refresh")
            .sameSite("Strict")
            .maxAge(token.ttl())
            .build();

        TokenResponse response = new TokenResponse(token.accessToken());
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new ApiResponse<>(true, "Refresh success", response));
    }
    
    
}
