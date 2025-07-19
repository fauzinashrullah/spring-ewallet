package com.fauzi.ewallet.user.web.mapper;

import com.fauzi.ewallet.user.application.command.UpdateCommand;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.web.dto.request.UpdateUserRequest;
import com.fauzi.ewallet.user.web.dto.response.UpdateUserResponse;

public class UserWebMapper {
    public static UpdateCommand toCommand(UpdateUserRequest request){
        return new UpdateCommand(request.getName(), request.getEmail());
    }

    public static UpdateUserResponse toResponse(UserResult result){
        return new UpdateUserResponse(result.authUserId().toString(), result.fullName(), result.email());
    }
}
