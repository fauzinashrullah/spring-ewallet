package com.fauzi.ewallet.auth.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.command.UpdatePasswordCommand;
import com.fauzi.ewallet.auth.application.mapper.AuthAppMapper;
import com.fauzi.ewallet.auth.application.result.GetAuthResult;
import com.fauzi.ewallet.auth.application.result.UserDataResult;
import com.fauzi.ewallet.auth.application.usecase.UserAuthUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.domain.repository.PasswordHasher;
import com.fauzi.ewallet.shared.exception.AlreadyExistsException;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.UserDetailsImpl;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.application.usecase.UserQueryUseCase;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAuthService implements UserAuthUseCase{
    
    private final AuthRepository repository;
    private final AuthService authService;
    private final UserQueryUseCase userQueryService;
    private final PasswordHasher passwordHasher;

    @Override
    public UserDataResult getCurrent (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UUID userId = userDetails.getId();
        UserResult user = userQueryService.findByAuthUserId(userId);
        
        return new UserDataResult(user.fullname(), user.username(), user.phoneNumber(), userDetails.getEmail());
    }

    @Override
    public String getAuthEmail (UUID id){
        return repository.findById(id).map(AuthAppMapper::toAuthResult)
            .orElseThrow(() -> new NotFoundException("User not found"))
            .email();
    }

    @Override
    public void updateEmail (String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        AuthUser authUser = repository.findById(userDetails.getId())
            .orElseThrow(() -> new NotFoundException("User not found"));

            if (authUser.getEmail().equals(email)){
                throw new AlreadyExistsException("Email must be different from the current one.");
            }
            if (repository.findByEmail(email).isPresent()){
                throw new AlreadyExistsException("Email is already in use");
            }
            
        authUser.updateEmail(email);
        repository.save(authUser);
    }

    @Override
    public void updatePassword (UpdatePasswordCommand command){
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

    @Override
    public void deleteUser (String authHeader){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        AuthUser authUser =  repository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));
        // if (!authUser.isActive()){
        //     throw new NotFoundException("User not found");
        // }
        authUser.deactivate();
        
        repository.save(authUser);
        authService.logout(authHeader);
    }
    @Override
    public List<GetAuthResult> getAllAuth (){
        return repository
            .findAllAuth()
            .stream()
            .map(AuthAppMapper::toAuthResult)
            .toList();
    }
}
