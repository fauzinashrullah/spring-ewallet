package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fauzi.ewallet.auth.domain.repository.AuthRepositoryPort;
import com.fauzi.ewallet.user.domain.User;
import com.fauzi.ewallet.user.infrastructure.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaAuthRepository implements AuthRepositoryPort {
    
    private final SpringDataAuthRepository authRepo;

    @Override
    public Optional<User> findByEmail(String email){
        return authRepo.findByEmail(email)
            .map(UserMapper::toDomain);
    }

    public void save(User user){
        authRepo.save(UserMapper.toEntity(user));
    }
}
