package com.fauzi.ewallet.auth.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.mapper.AuthAppMapper;
import com.fauzi.ewallet.auth.application.result.GetAuthResult;
import com.fauzi.ewallet.auth.application.usecase.GetAllAuthUseCase;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAllAuthService implements GetAllAuthUseCase{
    
    private final AuthRepository repository;

    public List<GetAuthResult> execute (){
        return repository
            .findAllAuth()
            .stream()
            .map(AuthAppMapper::toAuthResult)
            .toList();
    }
}
