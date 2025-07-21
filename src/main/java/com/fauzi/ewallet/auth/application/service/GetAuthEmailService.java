package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.mapper.AuthAppMapper;
import com.fauzi.ewallet.auth.application.usecase.GetAuthEmailUseCase;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.shared.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAuthEmailService implements GetAuthEmailUseCase{
    
    private final AuthRepository repository;
    
    public String execute (UUID id){
        return repository.findById(id).map(AuthAppMapper::toAuthResult)
            .orElseThrow(() -> new NotFoundException("User not found"))
            .email();
    }
}
