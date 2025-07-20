package com.fauzi.ewallet.auth.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.usecase.GetCurrentUserUseCase;
import com.fauzi.ewallet.auth.web.dto.response.UserResponse;
import com.fauzi.ewallet.auth.web.mapper.ApiMapper;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class GetCurrentUserController {
    
    private final GetCurrentUserUseCase getCurrentUser;

    @PostMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> me() {
        UserResponse response = ApiMapper.toUserResponse(getCurrentUser.execute());
        return ResponseEntity.ok(new ApiResponse<>(true, "Get user success", response));
    }
}
