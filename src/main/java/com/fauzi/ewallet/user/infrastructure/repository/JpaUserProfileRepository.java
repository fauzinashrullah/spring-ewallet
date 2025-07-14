package com.fauzi.ewallet.user.infrastructure.repository;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fauzi.ewallet.user.domain.model.User;
import com.fauzi.ewallet.user.domain.repository.UserProfileRepository;
import com.fauzi.ewallet.user.infrastructure.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaUserProfileRepository implements UserProfileRepository{
    private final SpringDataUserRepository repository;

    public void save(User user){
        repository.save(UserMapper.toEntity(user));
    }

    public User findByAuthUserId(UUID id){
        return UserMapper.toDomain(repository.findByAuthUserId(id)
            .orElseThrow(() -> new RuntimeException("id not found"))
        );
    }
}
