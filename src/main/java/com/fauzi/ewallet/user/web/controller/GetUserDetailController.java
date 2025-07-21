package com.fauzi.ewallet.user.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.shared.common.dto.ApiResponse;
import com.fauzi.ewallet.user.application.usecase.GetUserDetailUseCase;
import com.fauzi.ewallet.user.web.dto.response.UserResponse;
import com.fauzi.ewallet.user.web.mapper.UserWebMapper;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class GetUserDetailController {
    
    private final GetUserDetailUseCase getUserDetail;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserDetail (@PathVariable UUID id) {
        UserResponse response = UserWebMapper.toResponse(getUserDetail.execute(id));
        return ResponseEntity.ok(new ApiResponse<>(true, "Get user detail success", response));
    }
    
}
