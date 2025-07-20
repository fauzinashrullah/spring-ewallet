package com.fauzi.ewallet.auth.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.usecase.UpdatePasswordUseCase;
import com.fauzi.ewallet.auth.web.dto.request.UpdatePasswordRequest;
import com.fauzi.ewallet.auth.web.mapper.ApiMapper;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UpdatePasswordController {
    
    private final UpdatePasswordUseCase updatePassword;

    @PutMapping("/password")
    public ResponseEntity<ApiResponse<?>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        updatePassword.execute(ApiMapper.toUpdatePasswordCommand(request));
        return ResponseEntity.ok(new ApiResponse<>(true, "Update password success", Map.of()));
    }
}
