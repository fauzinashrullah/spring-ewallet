package com.fauzi.ewallet.auth.web.mapper;

import com.fauzi.ewallet.auth.application.command.UpdatePasswordCommand;
import com.fauzi.ewallet.auth.application.result.UserAuthResult;
import com.fauzi.ewallet.auth.web.dto.request.UpdatePasswordRequest;
import com.fauzi.ewallet.auth.web.dto.response.UserResponse;

public class ApiMapper {
    public static UserResponse toUserResponse (UserAuthResult command){
        return new UserResponse(command.name(), command.email());
    }

    public static UpdatePasswordCommand toUpdatePasswordCommand(UpdatePasswordRequest request){
        return new UpdatePasswordCommand(request.getOldPassword(), request.getNewPassword());
    }
}
