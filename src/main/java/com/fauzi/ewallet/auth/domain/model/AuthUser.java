package com.fauzi.ewallet.auth.domain.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUser {
    private UUID id;
    private String email;
    private String password;
    private Role role;

    public void updateEmail (String email){
        this.email = email;
    }
}
