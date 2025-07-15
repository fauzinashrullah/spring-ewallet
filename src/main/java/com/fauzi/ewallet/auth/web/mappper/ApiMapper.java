package com.fauzi.ewallet.auth.web.mappper;

import com.fauzi.ewallet.auth.application.result.UserResult;
import com.fauzi.ewallet.auth.web.dto.response.UserResponse;

public class ApiMapper {
    public static UserResponse from (UserResult command){
        return new UserResponse(command.name(), command.email());
    }
}
