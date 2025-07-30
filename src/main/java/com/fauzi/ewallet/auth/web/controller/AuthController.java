package com.fauzi.ewallet.auth.web.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.result.TokenResult;
import com.fauzi.ewallet.auth.application.usecase.AuthUseCase;
import com.fauzi.ewallet.auth.web.dto.request.LoginRequest;
import com.fauzi.ewallet.auth.web.dto.request.RegisterRequest;
import com.fauzi.ewallet.auth.web.dto.response.TokenResponse;
import com.fauzi.ewallet.auth.web.dto.response.UserProfileResponse;
import com.fauzi.ewallet.auth.web.helper.CookieUtil;
import com.fauzi.ewallet.auth.web.mapper.AuthWebMapper;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthUseCase authUseCase;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest request) {

        TokenResult token = authUseCase.login(AuthWebMapper.toCommand(request));
        ResponseCookie cookie = CookieUtil.createRefreshTokenCookie(token);

        TokenResponse response = new TokenResponse(token.accessToken());
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new ApiResponse<>(true, "Login success", response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserProfileResponse>> register(@Valid @RequestBody RegisterRequest request) {

        UserProfileResponse userResponse = AuthWebMapper.toResponse(authUseCase.register(AuthWebMapper.toCommand(request)));

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ApiResponse<>(true, "Register success", userResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenResponse>> refresh(@CookieValue("refresh_token") String refreshToken) {
        
        TokenResult token = authUseCase.refresh(refreshToken);
        ResponseCookie cookie = CookieUtil.createRefreshTokenCookie(token);

        TokenResponse response = new TokenResponse(token.accessToken());
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new ApiResponse<>(true, "Refresh success", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@RequestHeader("Authorization") String authHeader) {
        
        authUseCase.logout(authHeader);
        return ResponseEntity.ok(new ApiResponse<>(true, "Logout success", Map.of()));
    }
}
