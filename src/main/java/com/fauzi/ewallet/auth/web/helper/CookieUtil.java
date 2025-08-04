package com.fauzi.ewallet.auth.web.helper;

import org.springframework.http.ResponseCookie;

import com.fauzi.ewallet.auth.application.inbound.dto.result.TokenResult;

public class CookieUtil {
    public static ResponseCookie createRefreshTokenCookie (TokenResult token){
         return ResponseCookie.from("refresh_token", token.refreshToken())
            .httpOnly(true)
            .secure(true)
            .path("/api/v1/auth/refresh")
            .sameSite("Strict")
            .maxAge(token.ttl())
            .build();
    }
}
