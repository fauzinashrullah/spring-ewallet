package com.fauzi.ewallet.auth.infrastructure.mapper;

import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.infrastructure.persistence.AuthEntity;

public class AuthMapper {
    public static AuthUser toDomain(AuthEntity e){
        return new AuthUser(e.getId(), e.getEmail(), e.getPassword(), null);
    }
    
    public static AuthEntity toEntity(AuthUser u){
        AuthEntity entity = new AuthEntity();
        entity.setId(u.getId());
        entity.setEmail(u.getEmail());
        entity.setPassword(u.getPassword());
        return entity;
    }
}
