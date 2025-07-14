package com.fauzi.ewallet.user.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fauzi.ewallet.user.infrastructure.persistence.UserEntity;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID>{
    Optional<UserEntity> findByAuthUserId(UUID id);
}
