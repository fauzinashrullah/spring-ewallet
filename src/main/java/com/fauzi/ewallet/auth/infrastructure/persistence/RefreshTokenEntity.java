package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RefreshTokenEntity {
    @Id
    private UUID id;
    private UUID userId;
    private String token;
    private Instant expiresAt;
}
