package com.fauzi.ewallet.auth.infrastructure.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fauzi.ewallet.auth.domain.repository.PasswordHasher;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BCryptPasswordHasher implements PasswordHasher{

    private final PasswordEncoder passwordEncoder;

    public String hash(String password){
        return passwordEncoder.encode(password);
    }

    public boolean verify(String password, String hash){
        return passwordEncoder.matches(password, hash);
    }
}
