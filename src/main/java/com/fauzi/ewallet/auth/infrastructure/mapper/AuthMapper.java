package com.fauzi.ewallet.auth.infrastructure.mapper;

import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.auth.infrastructure.persistence.AuthEntity;

public class AuthMapper {
    public static AuthUser toDomain(AuthEntity e){
        return new AuthUser(e.getId(), e.getEmail(), e.getPassword(), e.getRole(), e.isActive(), e.getDeletedAt(), e.getCreatedAt());
    }
    
    public static AuthEntity toEntity(AuthUser u){
        return new AuthEntity(u.getId(), u.getEmail(), u.getPassword(), u.getRole(), u.isActive(), u.getDeletedAt(), u.getCreatedAt());
    }
}
