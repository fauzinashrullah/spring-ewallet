package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<AuthUser> findById(UUID id){
        return authRepo.findById(id)
            .map(AuthMapper::toDomain);
    }

    @Override
    public void save(AuthUser user){
        authRepo.save(AuthMapper.toEntity(user));
    }

    @Override
    public List<AuthUser> findAllAuth (){
        return authRepo.findAll().stream().map(AuthMapper::toDomain).toList();
    }
}
