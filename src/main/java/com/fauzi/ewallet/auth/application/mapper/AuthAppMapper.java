package com.fauzi.ewallet.auth.application.mapper;

import com.fauzi.ewallet.auth.application.result.GetAuthResult;
import com.fauzi.ewallet.auth.domain.model.AuthUser;

public class AuthAppMapper {
    public static GetAuthResult toAuthResult (AuthUser u){
        return new GetAuthResult(u.getId(), u.getEmail());
    }
}
