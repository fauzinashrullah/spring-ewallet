package com.fauzi.ewallet.user.infrastructure.mapper;

import com.fauzi.ewallet.user.domain.model.User;
import com.fauzi.ewallet.user.infrastructure.persistence.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity e){
        return new User(e.getAuthUserId(), e.getFullname(), e.getPhoneNumber());
    }
    
    public static UserEntity toEntity(User u){
        UserEntity user = new UserEntity();
        user.setAuthUserId(u.getAuthUserId());
        user.setFullname(u.getFullName());
        user.setPhoneNumber(u.getPhoneNumber());
        return user;
    }

    
}
