package com.fauzi.ewallet.user.application.mapper;

import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.domain.model.User;

public class UserAppMapper {
    public static UserResult toResult (User user, String email){
        return new UserResult(user.getAuthUserId(), user.getFullname(), user.getUsername(), user.getPhoneNumber(), email);
    }
}
