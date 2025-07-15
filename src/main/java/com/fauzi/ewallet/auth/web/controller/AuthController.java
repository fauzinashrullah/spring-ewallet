package com.fauzi.ewallet.auth.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.dto.TokenResponse;
import com.fauzi.ewallet.auth.application.usecase.GetCurrentUserUseCase;
import com.fauzi.ewallet.auth.application.usecase.LoginUseCase;
import com.fauzi.ewallet.auth.application.usecase.LogoutUseCase;
import com.fauzi.ewallet.auth.application.usecase.RefreshUseCase;
import com.fauzi.ewallet.auth.application.usecase.RegisterUseCase;
import com.fauzi.ewallet.auth.web.dto.CurrentUserResponse;
import com.fauzi.ewallet.auth.web.dto.LoginRequest;
import com.fauzi.ewallet.auth.web.dto.LoginResponse;
import com.fauzi.ewallet.auth.web.dto.RegisterRequest;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
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
    private final JwtTokenProvider jwt;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        register.execute(request);
        return ResponseEntity.ok("ok");
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = login.execute(request);
        TokenResponse token = response.getToken();
        ResponseCookie cookie = ResponseCookie.from("refresh_token", token.getRefreshToken())
            .httpOnly(true)
            .secure(true) // false jika di localhost
            .path("/api/v1/auth/refresh")
            .sameSite("Strict")
            .maxAge(jwt.getExpiration(token.getRefreshToken()))
            .build();

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(token.getAccessToken());
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
    
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue("refresh_token") String refreshToken) {
        TokenResponse token = refresh.execute(refreshToken);
        ResponseCookie cookie = ResponseCookie.from("refresh_token", token.getRefreshToken())
            .httpOnly(true)
            .secure(true) // false jika di localhost
            .path("/api/v1/auth/refresh")
            .sameSite("Strict")
            .maxAge(jwt.getExpiration(token.getRefreshToken()))
            .build();

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(token.getAccessToken());
    }
    
    
}
