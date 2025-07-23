package com.fauzi.ewallet.user.web.mapper;

import com.fauzi.ewallet.user.application.command.UpdateCommand;
import com.fauzi.ewallet.user.application.result.UpdateUserResult;
import com.fauzi.ewallet.user.application.result.UserResult;
import com.fauzi.ewallet.user.web.dto.request.UpdateUserRequest;
import com.fauzi.ewallet.user.web.dto.response.UpdateUserResponse;
import com.fauzi.ewallet.user.web.dto.response.UserResponse;

public class UserWebMapper {
    public static UpdateCommand toCommand(UpdateUserRequest request){
        return new UpdateCommand(request.getName());
    }

    public static UserResponse toResponse(UserResult result){
        return new UserResponse(result.authUserId().toString(), result.fullName(), result.email());
    }

    public static UpdateUserResponse toUpdateResponse(UpdateUserResult result){
        return new UpdateUserResponse(result.userId().toString(), result.fullname());
    }
}
