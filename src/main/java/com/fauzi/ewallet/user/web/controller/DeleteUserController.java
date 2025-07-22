package com.fauzi.ewallet.user.web.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.usecase.DeleteUserUseCase;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class DeleteUserController {
    
    private final DeleteUserUseCase deleteUser;

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<?>> deleteUser (@RequestHeader("Authorization") String authHeader){
        deleteUser.execute(authHeader);
        return ResponseEntity.ok(new ApiResponse<>(true, "Delete user success", Map.of()));
    }
}
