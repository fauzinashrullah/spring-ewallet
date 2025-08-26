package com.fauzi.ewallet.user.web.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.auth.application.inbound.usecase.UserAuthUseCase;
import com.fauzi.ewallet.shared.common.dto.ApiResponse;
import com.fauzi.ewallet.user.application.inbound.usecase.GetAllUsersUseCase;
import com.fauzi.ewallet.user.application.inbound.usecase.GetUserDetailUseCase;
import com.fauzi.ewallet.user.application.inbound.usecase.UpdateUserUseCase;
import com.fauzi.ewallet.user.web.dto.request.UpdateUserRequest;
import com.fauzi.ewallet.user.web.dto.response.UpdateUserResponse;
import com.fauzi.ewallet.user.web.dto.response.UserResponse;
import com.fauzi.ewallet.user.web.mapper.UserWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class UserProfileController {
    
    private final GetUserDetailUseCase getUserDetail;
    private final GetAllUsersUseCase getAllUsers;
    private final UpdateUserUseCase updateUserUseCase;
    private final UserAuthUseCase userAuth;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserDetail (@PathVariable UUID id) {
        UserResponse response = UserWebMapper.toResponse(getUserDetail.execute(id));
        return ResponseEntity.ok(new ApiResponse<>(true, "Get user detail success", response));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> responses = getAllUsers.execute().stream().map(UserWebMapper::toResponse).toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "Get all user success", responses));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UpdateUserResponse>> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        UpdateUserResponse response = UserWebMapper.toUpdateResponse(updateUserUseCase.execute(UserWebMapper.toCommand(request)));
        return ResponseEntity.ok(new ApiResponse<>(true, "Update user success", response));
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<?>> deleteUser (@RequestHeader("Authorization") String authHeader){
        userAuth.deleteUser(authHeader);
        return ResponseEntity.ok(new ApiResponse<>(true, "Delete user success", Map.of()));
    }
}
