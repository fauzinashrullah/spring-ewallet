package com.fauzi.ewallet.auth.infrastructure.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.infrastructure.persistence.AuthEntity;
import com.fauzi.ewallet.auth.infrastructure.persistence.SpringDataAuthRepository;
import com.fauzi.ewallet.shared.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SpringDataAuthRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthEntity user = repository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
