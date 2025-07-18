package com.fauzi.ewallet.auth.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.usecase.GetCurrentUserUseCase;
import com.fauzi.ewallet.auth.web.dto.response.ApiResponse;
import com.fauzi.ewallet.auth.web.dto.response.UserResponse;
import com.fauzi.ewallet.auth.web.helper.ApiMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class GetCurrentUserController {
    
    private final GetCurrentUserUseCase getCurrentUser;

    @PostMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> me(@RequestHeader("Authorization") String authHeader) {
        UserResponse response = ApiMapper.from(getCurrentUser.execute(authHeader));
        return ResponseEntity.ok(new ApiResponse<>(true, "Get user success", response));
    }
}
