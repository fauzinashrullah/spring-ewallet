package com.fauzi.ewallet.user.application.service;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.security.UserDetailsImpl;
import com.fauzi.ewallet.user.application.command.UpdateCommand;
import com.fauzi.ewallet.user.application.result.UpdateUserResult;
import com.fauzi.ewallet.user.application.usecase.UpdateUserUseCase;
import com.fauzi.ewallet.user.domain.model.User;
import com.fauzi.ewallet.user.domain.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UsersRepository repository;

    public UpdateUserResult execute (UpdateCommand command){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UUID userId = userDetails.getId();
        User user = repository.findByAuthUserId(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));
        
        user.setName(command.name());
        repository.save(user);
        return new UpdateUserResult(userId, user.getFullName());
    }
}
