package com.fauzi.ewallet.user.application.outbound.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.shared.exception.AlreadyExistsException;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.user.application.outbound.dto.result.UserProfileResult;
import com.fauzi.ewallet.user.application.outbound.dto.result.UserSummaryResult;
import com.fauzi.ewallet.user.application.outbound.usecase.UserCommandUseCase;
import com.fauzi.ewallet.user.application.outbound.usecase.UserQueryUseCase;
import com.fauzi.ewallet.user.domain.model.User;
import com.fauzi.ewallet.user.domain.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserCommandUseCase, UserQueryUseCase{
    
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
    public UserSummaryResult findByAuthUserId(UUID id){
        User user = repository.findByAuthUserId(id)
            .orElseThrow(() -> new NotFoundException("User not found"));
        return new UserSummaryResult(user.getAuthUserId(), user.getFullname(), user.getUsername(), user.getPhoneNumber(), null);
    }

    @Override 
    public boolean existByUsername (String username){
        return repository.findByUsername(username).isPresent();
    }
}
