package com.fauzi.ewallet.auth.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.usecase.UserAuthUseCase;
import com.fauzi.ewallet.auth.web.dto.response.AuthUserResponse;
import com.fauzi.ewallet.auth.web.mapper.ApiMapper;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class GetCurrentUserController {
    
    private final UserAuthUseCase userAuth;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<AuthUserResponse>> me() {
        AuthUserResponse response = ApiMapper.toUserResponse(userAuth.getCurrent());
        return ResponseEntity.ok(new ApiResponse<>(true, "Get user success", response));
    }
}
