package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.repository.AuthRepository;
import com.fauzi.ewallet.auth.infrastructure.mapper.AuthMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaAuthRepository implements AuthRepository {
    
    private final SpringDataAuthRepository authRepo;

    @Override
    public Optional<AuthUser> findByEmail(String email){
        return authRepo.findByEmail(email)
            .map(AuthMapper::toDomain);
    }

    @Override
    public void save(AuthUser user){
        authRepo.save(AuthMapper.toEntity(user));
    }
}
