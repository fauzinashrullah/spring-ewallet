package com.fauzi.ewallet.auth.web.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.usecase.LogoutUseCase;
import com.fauzi.ewallet.auth.web.dto.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LogoutUserController {
    
    private final LogoutUseCase logout;

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@RequestHeader("Authorization") String authHeader) {
        logout.execute(authHeader);
        return ResponseEntity.ok(new ApiResponse<>(true, "Logout success", Map.of()));
    }
}
