package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fauzi.ewallet.user.infrastructure.persistence.UserEntity;

public interface SpringDataAuthRepository extends JpaRepository<UserEntity, UUID>{
    Optional<UserEntity> findByEmail(String email);
}
