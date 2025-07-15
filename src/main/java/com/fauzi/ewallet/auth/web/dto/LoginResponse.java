package com.fauzi.ewallet.auth.web.dto;

import com.fauzi.ewallet.auth.application.dto.TokenResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String name;
    private String email;
    private TokenResponse token;
}
