package com.fauzi.ewallet.auth.application.service;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.command.UpdatePasswordCommand;
import com.fauzi.ewallet.auth.application.usecase.UpdatePasswordUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.domain.repository.PasswordHasher;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdatePasswordService implements UpdatePasswordUseCase{
    
    private final AuthRepository repository;
    private final PasswordHasher passwordHasher;

    public void execute (UpdatePasswordCommand command){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        AuthUser authUser = repository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));

        if(!passwordHasher.verify(command.oldPassword(), authUser.getPassword())){
            throw new UnauthorizedException("Failed to update password");
        }
        authUser.updatePassword(passwordHasher.hash(command.newPassword()));
        repository.save(authUser);
    }
}
