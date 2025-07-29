package com.fauzi.ewallet.auth.web.mapper;

import com.fauzi.ewallet.auth.application.command.UpdatePasswordCommand;
import com.fauzi.ewallet.auth.application.result.RegisterResult;
import com.fauzi.ewallet.auth.application.result.UserAuthResult;
import com.fauzi.ewallet.auth.web.dto.request.UpdatePasswordRequest;
import com.fauzi.ewallet.auth.web.dto.response.UserProfileResponse;

public class ApiMapper {
    public static UserProfileResponse toResponse (UserAuthResult command){
        return new UserProfileResponse(command.name(), null, null, command.email());
    }

    public static UserProfileResponse toResponse (RegisterResult result){
        return new UserProfileResponse(
            result.name(), 
            result.username(), 
            result.phoneNumber(), 
            result.email()
            );
    }

    public static UpdatePasswordCommand toUpdatePasswordCommand(UpdatePasswordRequest request){
        return new UpdatePasswordCommand(request.getOldPassword(), request.getNewPassword());
    }
}
