package com.fauzi.ewallet.user.application.inbound.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.outbound.usecase.GetAuthUseCase;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.user.application.inbound.dto.result.UserResult;
import com.fauzi.ewallet.user.application.inbound.usecase.GetAllUsersUseCase;
import com.fauzi.ewallet.user.domain.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAllUsersService implements GetAllUsersUseCase {

    private final UsersRepository usersRepository;
    private final GetAuthUseCase getAuth;
    
    public List<UserResult> execute (){
        return usersRepository.findAllUsers().stream().map(user -> {
            String userEmail = getAuth.getAllAuth().stream()
                .filter(auth -> auth.id().equals(user.getAuthUserId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User not found"))
                .email();
            return new UserResult(user.getAuthUserId(), user.getFullname(), user.getUsername(), user.getPhoneNumber(), userEmail);
        }).toList();
    }
}
