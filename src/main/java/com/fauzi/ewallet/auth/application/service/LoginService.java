package com.fauzi.ewallet.auth.application.service;

import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.application.usecase.LoginUseCase;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.infrastructure.mapper.PasswordHasher;
import com.fauzi.ewallet.auth.web.dto.LoginRequest;
import com.fauzi.ewallet.auth.web.dto.LoginResponse;
import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.exception.UnauthorizedException;
import com.fauzi.ewallet.shared.security.JwtTokenProvider;
import com.fauzi.ewallet.user.application.usecase.UserQueryService;
import com.fauzi.ewallet.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase{
    private final AuthRepository authRepository;
    private final PasswordHasher passwordHasher;
    private final UserQueryService userQueryService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse execute(LoginRequest request){
        AuthUser authUser = authRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new NotFoundException("email not found"));

        if (!passwordHasher.verify(request.getPassword(), authUser.getPassword())){
            throw new UnauthorizedException("Password not match");
        }
        User user = userQueryService.findByAuthUserId(authUser.getId());
        String token = jwtTokenProvider.generateToken(authUser.getId(), authUser.getEmail());

        return new LoginResponse(user.getFullName(), authUser.getEmail(), token);
    }
}
