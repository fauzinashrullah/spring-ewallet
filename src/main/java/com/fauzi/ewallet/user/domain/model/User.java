package com.fauzi.ewallet.user.domain.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private UUID authUserId;
    private String fullName;

    public void setName (String name){
        this.fullName = name;
    }
}
