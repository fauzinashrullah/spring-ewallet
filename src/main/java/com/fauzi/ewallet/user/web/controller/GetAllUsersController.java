package com.fauzi.ewallet.user.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.shared.common.dto.ApiResponse;
import com.fauzi.ewallet.user.application.inbound.usecase.GetAllUsersUseCase;
import com.fauzi.ewallet.user.web.dto.response.UserResponse;
import com.fauzi.ewallet.user.web.mapper.UserWebMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class GetAllUsersController {
    
    private final GetAllUsersUseCase getAllUsers;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> responses = getAllUsers.execute().stream().map(UserWebMapper::toResponse).toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "Get all user success", responses));
    }
    
}
