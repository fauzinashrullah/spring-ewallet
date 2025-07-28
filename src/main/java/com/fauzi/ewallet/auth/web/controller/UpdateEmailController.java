package com.fauzi.ewallet.auth.web.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.usecase.UserAuthUseCase;
import com.fauzi.ewallet.auth.web.dto.request.UpdateEmailRequest;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UpdateEmailController {
    
    private final UserAuthUseCase userAuth;

    @PutMapping("/email")
    public ResponseEntity<ApiResponse<?>> updateEmail (@Valid @RequestBody UpdateEmailRequest request){
        userAuth.updateEmail(request.getEmail());
        return ResponseEntity.ok(new ApiResponse<>(true, "Update email success", Map.of()));
    }
}
