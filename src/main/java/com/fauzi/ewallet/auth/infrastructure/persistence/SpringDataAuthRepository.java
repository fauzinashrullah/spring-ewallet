package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAuthRepository extends JpaRepository<AuthEntity, UUID>{
    Optional<AuthEntity> findByEmail(String email);
}
