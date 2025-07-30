package com.fauzi.ewallet.auth.web.mapper;

import com.fauzi.ewallet.auth.application.command.LoginCommand;
import com.fauzi.ewallet.auth.application.command.RegisterCommand;
import com.fauzi.ewallet.auth.application.command.UpdatePasswordCommand;
import com.fauzi.ewallet.auth.application.result.UserDataResult;
import com.fauzi.ewallet.auth.application.result.UserAuthResult;
import com.fauzi.ewallet.auth.web.dto.request.LoginRequest;
import com.fauzi.ewallet.auth.web.dto.request.RegisterRequest;
import com.fauzi.ewallet.auth.web.dto.request.UpdatePasswordRequest;
import com.fauzi.ewallet.auth.web.dto.response.UserProfileResponse;

public class AuthWebMapper {
    public static UserProfileResponse toResponse (UserAuthResult res){
        return new UserProfileResponse(
            res.name(), 
            null, 
            null, 
            res.email()
            );
    }

    public static UserProfileResponse toResponse (UserDataResult res){
        return new UserProfileResponse(
            res.name(), 
            res.username(), 
            res.phoneNumber(), 
            res.email()
            );
    }

    public static UpdatePasswordCommand toCommand(UpdatePasswordRequest req){
        return new UpdatePasswordCommand(
            req.getOldPassword(), 
            req.getNewPassword()
            );
    }

    public static RegisterCommand toCommand (RegisterRequest req){
        return new RegisterCommand(
            req.getName(), 
            req.getUsername(), 
            req.getPhoneNumber(), 
            req.getEmail(), 
            req.getPassword()
            );
    }

    public static LoginCommand toCommand (LoginRequest req){
        return new LoginCommand(
            req.getEmail(), 
            req.getPassword()
            );
    }
}
