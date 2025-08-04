package com.fauzi.ewallet.user.application.inbound.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.outbound.usecase.GetAuthUseCase;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.user.application.inbound.dto.result.UserResult;
import com.fauzi.ewallet.user.application.inbound.usecase.GetUserDetailUseCase;
import com.fauzi.ewallet.user.application.shared.mapper.UserAppMapper;
import com.fauzi.ewallet.user.domain.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserDetailService implements GetUserDetailUseCase{
    
    private final UsersRepository repository;
    private final GetAuthUseCase getAuth;

    public UserResult execute (UUID id){
        return repository.findByAuthUserId(id)
            .map(user -> UserAppMapper.toResult(user, getAuth.getAuthEmail(id)))
            .orElseThrow(() -> new NotFoundException("User not found"));
        
    }
}
