package com.fauzi.ewallet.user.infrastructure.mapper;

import com.fauzi.ewallet.user.domain.User;
import com.fauzi.ewallet.user.infrastructure.persistence.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity e){
        return new User(e.getId(), e.getName(), e.getEmail(), e.getPassword());
    }
    
    public static UserEntity toEntity(User u){
        UserEntity user = new UserEntity();
        user.setId(u.getId());
        user.setName(u.getName());
        user.setEmail(u.getEmail());
        user.setPassword(u.getPassword());
        return user;
    }
}
