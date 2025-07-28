package com.fauzi.ewallet.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.UserAuthUseCase;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.user.application.mapper.UserAppMapper;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.application.usecase.GetUserDetailUseCase;
import com.fauzi.ewallet.user.domain.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserDetailService implements GetUserDetailUseCase{
    
    private final UsersRepository repository;
    private final UserAuthUseCase userAuth;

    public UserResult execute (UUID id){
        return repository.findByAuthUserId(id)
            .map(user -> UserAppMapper.toResult(user, userAuth.getAuthEmail(id)))
            .orElseThrow(() -> new NotFoundException("User not found"));
        
    }
}
