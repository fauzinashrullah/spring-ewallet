package com.fauzi.ewallet.user.application.mapper;

import com.fauzi.ewallet.auth.application.result.GetAuthResult;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.domain.model.User;

public class UserAppMapper {
    public static UserResult toResult (User user, GetAuthResult authResult){
        return new UserResult(user.getAuthUserId(), user.getFullName(), authResult.email());
    }
}
