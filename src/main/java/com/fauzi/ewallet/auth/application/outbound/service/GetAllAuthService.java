package com.fauzi.ewallet.auth.application.outbound.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.outbound.dto.result.GetAuthResult;
import com.fauzi.ewallet.auth.application.outbound.usecase.GetAuthUseCase;
import com.fauzi.ewallet.auth.application.shared.mapper.AuthAppMapper;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.shared.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetAllAuthService implements GetAuthUseCase{

    private final AuthRepository repository;
    
    @Override
    public List<GetAuthResult> getAllAuth (){
        return repository
            .findAllAuth()
            .stream()
            .map(AuthAppMapper::toAuthResult)
            .toList();
    }

    @Override
    public String getAuthEmail (UUID id){
        return repository.findById(id).map(AuthAppMapper::toAuthResult)
            .orElseThrow(() -> new NotFoundException("User not found"))
            .email();
    }
}
