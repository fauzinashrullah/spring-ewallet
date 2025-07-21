package com.fauzi.ewallet.user.infrastructure.mapper;

import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.domain.model.User;
import com.fauzi.ewallet.user.infrastructure.persistence.UserEntity;
import com.fauzi.ewallet.user.web.dto.response.UserResponse;

public class UserMapper {
    public static User toDomain(UserEntity e){
        return new User(e.getAuthUserId(), e.getFullName());
    }
    
    public static UserEntity toEntity(User u){
        UserEntity user = new UserEntity();
        user.setAuthUserId(u.getAuthUserId());
        user.setFullName(u.getFullName());
        return user;
    }

    
}
