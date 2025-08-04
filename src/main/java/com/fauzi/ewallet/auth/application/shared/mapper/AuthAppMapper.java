package com.fauzi.ewallet.auth.application.shared.mapper;

import com.fauzi.ewallet.auth.application.inbound.dto.result.UserDataResult;
import com.fauzi.ewallet.auth.application.outbound.dto.result.GetAuthResult;
import com.fauzi.ewallet.auth.domain.model.AuthUser;
import com.fauzi.ewallet.user.application.outbound.dto.result.UserSummaryResult;

public class AuthAppMapper {
    public static GetAuthResult toAuthResult (AuthUser u){
        return new GetAuthResult(u.getId(), u.getEmail());
    }
    public static UserDataResult toDataResult (UserSummaryResult result, String email){
        return new UserDataResult(result.fullname(), result.username(), result.phoneNumber(), email);
    }
}