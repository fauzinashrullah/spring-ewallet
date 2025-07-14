package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SpringDataTokenRepository extends JpaRepository<RefreshTokenEntity, UUID>{
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByUserId(UUID userId);
}
