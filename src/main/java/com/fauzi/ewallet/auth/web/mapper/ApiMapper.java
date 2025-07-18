package com.fauzi.ewallet.auth.web.mapper;

import com.fauzi.ewallet.auth.application.result.UserAuthResult;
import com.fauzi.ewallet.auth.web.dto.response.UserResponse;

public class ApiMapper {
    public static UserResponse from (UserAuthResult command){
        return new UserResponse(command.name(), command.email());
    }
}
