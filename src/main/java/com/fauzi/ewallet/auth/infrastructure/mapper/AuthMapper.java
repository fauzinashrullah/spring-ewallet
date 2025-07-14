package com.fauzi.ewallet.auth.infrastructure.mapper;

import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.domain.model.RefreshToken;
import com.fauzi.ewallet.auth.infrastructure.persistence.AuthEntity;
import com.fauzi.ewallet.auth.infrastructure.persistence.RefreshTokenEntity;

public class AuthMapper {
    public static AuthUser toDomain(AuthEntity e){
        return new AuthUser(e.getId(), e.getEmail(), e.getPassword());
    }
    
    public static AuthEntity toEntity(AuthUser u){
        AuthEntity entity = new AuthEntity();
        entity.setId(u.getId());
        entity.setEmail(u.getEmail());
        entity.setPassword(u.getPassword());
        return entity;
    }

    public static RefreshToken RefreshTokenToDomain(RefreshTokenEntity e){
        return new RefreshToken(e.getId(), e.getUserId(), e.getToken(), e.getExpiresAt());
    }
    public static RefreshTokenEntity RefreshTokenToEntity(RefreshToken r){
        RefreshTokenEntity token = new RefreshTokenEntity();
        token.setId(r.getId());
        token.setUserId(r.getUserId());
        token.setToken(r.getToken());
        token.setExpiresAt(r.getExpiresAt());
        return token;
    }
}
