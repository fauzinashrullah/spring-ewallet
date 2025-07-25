package com.fauzi.ewallet.user.domain.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private UUID authUserId;
    private String fullname;
    private String username;
    private String phoneNumber;

    public void setName (String name){
        this.fullname = name;
    }
}
