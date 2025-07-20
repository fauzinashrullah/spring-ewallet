package com.fauzi.ewallet.user.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.shared.common.dto.ApiResponse;
import com.fauzi.ewallet.user.application.usecase.UpdateUserUseCase;
import com.fauzi.ewallet.user.web.dto.request.UpdateUserRequest;
import com.fauzi.ewallet.user.web.dto.response.UpdateUserResponse;
import com.fauzi.ewallet.user.web.mapper.UserWebMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UpdateUserController {
    
    private final UpdateUserUseCase updateUserUseCase;

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UpdateUserResponse>> updateUser(@RequestBody UpdateUserRequest request) {
        UpdateUserResponse response = UserWebMapper.toResponse(updateUserUseCase.execute(UserWebMapper.toCommand(request)));
        return ResponseEntity.ok(new ApiResponse<>(true, "Update user success", response));
    }
}
