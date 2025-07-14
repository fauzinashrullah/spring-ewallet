package com.fauzi.ewallet.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.user.application.usecase.UserCommandService;
import com.fauzi.ewallet.user.application.usecase.UserQueryService;
import com.fauzi.ewallet.user.domain.model.User;
import com.fauzi.ewallet.user.domain.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService, UserQueryService{
    
    private final UserProfileRepository repository;

    public void createProfile (UUID authUserId, String fullName){
        User entity = new User(authUserId, fullName);
        repository.save(entity);
    }

    public User findByAuthUserId(UUID id){
        return repository.findByAuthUserId(id);
    }
}
