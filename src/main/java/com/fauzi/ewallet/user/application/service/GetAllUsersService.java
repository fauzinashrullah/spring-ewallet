package com.fauzi.ewallet.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.UserAuthUseCase;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.application.usecase.GetAllUsersUseCase;
import com.fauzi.ewallet.user.domain.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAllUsersService implements GetAllUsersUseCase {

    private final UsersRepository usersRepository;
    private final UserAuthUseCase userAuth;
    
    public List<UserResult> execute (){
        return usersRepository.findAllUsers().stream().map(user -> {
            String userEmail = userAuth.getAllAuth().stream()
                .filter(auth -> auth.id().equals(user.getAuthUserId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User not found"))
                .email();
            return new UserResult(user.getAuthUserId(), user.getFullname(), user.getUsername(), userEmail);
        }).toList();
    }
}
