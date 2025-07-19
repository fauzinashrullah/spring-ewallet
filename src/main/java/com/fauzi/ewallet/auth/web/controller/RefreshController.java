package com.fauzi.ewallet.auth.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.result.TokenResult;
import com.fauzi.ewallet.auth.application.usecase.RefreshUseCase;
import com.fauzi.ewallet.auth.web.dto.response.TokenResponse;
import com.fauzi.ewallet.auth.web.helper.CookieUtil;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RefreshController {
    
    private final RefreshUseCase refresh;
    
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenResponse>> refresh(@CookieValue("refresh_token") String refreshToken) {
        TokenResult token = refresh.execute(refreshToken);
        ResponseCookie cookie = CookieUtil.createRefreshTokenCookie(token);

        TokenResponse response = new TokenResponse(token.accessToken());
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new ApiResponse<>(true, "Refresh success", response));
    }
}
