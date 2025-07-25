package com.fauzi.ewallet.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.application.usecase.UserCommandService;
import com.fauzi.ewallet.user.application.usecase.UserQueryService;
import com.fauzi.ewallet.user.domain.model.User;
import com.fauzi.ewallet.user.domain.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService, UserQueryService{
    
    private final UsersRepository repository;

    @Override
    public void createProfile (UUID authUserId, String fullname, String username, String phoneNumber){
        User entity = new User(authUserId, fullname, username, phoneNumber);
        repository.save(entity);
    }

    @Override
    public UserResult findByAuthUserId(UUID id){
        User user = repository.findByAuthUserId(id)
            .orElseThrow(() -> new NotFoundException("User not found"));
        return new UserResult(user.getAuthUserId(), user.getFullname(), user.getUsername(), null);
    }
}
