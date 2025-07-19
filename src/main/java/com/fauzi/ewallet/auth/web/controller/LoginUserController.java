package com.fauzi.ewallet.auth.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.command.LoginCommand;
import com.fauzi.ewallet.auth.application.result.TokenResult;
import com.fauzi.ewallet.auth.application.usecase.LoginUseCase;
import com.fauzi.ewallet.auth.web.dto.request.LoginRequest;
import com.fauzi.ewallet.auth.web.dto.response.TokenResponse;
import com.fauzi.ewallet.auth.web.helper.CookieUtil;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginUserController {
    
    private final LoginUseCase login;
       
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginCommand command = new LoginCommand(request.getEmail(), request.getPassword());
        TokenResult token = login.execute(command);
        ResponseCookie cookie = CookieUtil.createRefreshTokenCookie(token);

        TokenResponse response = new TokenResponse(token.accessToken());
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new ApiResponse<>(true, "Login success", response));
    }
}
