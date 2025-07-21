package com.fauzi.ewallet.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.GetAuthEmailUseCase;
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
    private final GetAuthEmailUseCase getAuthEmail;

    public UserResult execute (UUID id){
        return repository.findByAuthUserId(id)
            .map(user -> UserAppMapper.toResult(user, getAuthEmail.execute(id)))
            .orElseThrow(() -> new NotFoundException("User not found"));
        
    }
}
