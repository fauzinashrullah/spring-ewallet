package com.fauzi.ewallet.auth.domain.model;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RefreshToken {
    private UUID id;
    private UUID userId;
    private String token;
    private Instant expiresAt;
    public RefreshToken (String token, UUID userId){
        this.token = token;
        this.userId = userId;
    }
}

