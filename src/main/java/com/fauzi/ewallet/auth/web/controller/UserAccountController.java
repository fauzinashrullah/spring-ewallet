package com.fauzi.ewallet.auth.web.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.inbound.usecase.UserAuthUseCase;
import com.fauzi.ewallet.auth.web.dto.request.UpdateEmailRequest;
import com.fauzi.ewallet.auth.web.dto.request.UpdatePasswordRequest;
import com.fauzi.ewallet.auth.web.dto.response.UserProfileResponse;
import com.fauzi.ewallet.auth.web.mapper.AuthWebMapper;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAccountController {
    
    private final UserAuthUseCase userAuth;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> me() {
        
        UserProfileResponse response = AuthWebMapper.toResponse(userAuth.getCurrent());
        return ResponseEntity.ok(new ApiResponse<>(true, "Get user success", response));
    }

    @PutMapping("/email")
    public ResponseEntity<ApiResponse<?>> updateEmail (@Valid @RequestBody UpdateEmailRequest request){
        
        userAuth.updateEmail(request.getEmail());
        return ResponseEntity.ok(new ApiResponse<>(true, "Update email success", Map.of()));
    }

    @PutMapping("/password")
    public ResponseEntity<ApiResponse<?>> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {

        userAuth.updatePassword(AuthWebMapper.toCommand(request));
        return ResponseEntity.ok(new ApiResponse<>(true, "Update password success", Map.of()));
    }
}
