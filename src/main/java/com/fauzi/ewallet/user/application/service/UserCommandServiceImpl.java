package com.fauzi.ewallet.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.shared.exception.AlreadyExistsException;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.user.application.result.UserProfileResult;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.application.usecase.UserCommandUseCase;
import com.fauzi.ewallet.user.application.usecase.UserQueryUseCase;
import com.fauzi.ewallet.user.domain.model.User;
import com.fauzi.ewallet.user.domain.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandUseCase, UserQueryUseCase{
    
    private final UsersRepository repository;

    @Override
    public UserProfileResult createProfile (UUID authUserId, String fullname, String username, String phoneNumber){
        if (repository.findByUsername(username).isPresent()){
            throw new AlreadyExistsException("Username is already in use");
        }
        User entity = new User(authUserId, fullname, username, phoneNumber);
        repository.save(entity);
        return new UserProfileResult(fullname, username, phoneNumber, null);
    }

    @Override
    public UserResult findByAuthUserId(UUID id){
        User user = repository.findByAuthUserId(id)
            .orElseThrow(() -> new NotFoundException("User not found"));
        return new UserResult(user.getAuthUserId(), user.getFullname(), user.getUsername(), null);
    }

    @Override 
    public boolean existByUsername (String username){
        return repository.findByUsername(username).isPresent();
    }
}
