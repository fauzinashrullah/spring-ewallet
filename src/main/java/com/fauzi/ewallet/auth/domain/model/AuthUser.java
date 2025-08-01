package com.fauzi.ewallet.auth.domain.model;

import java.time.LocalDateTime;
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
    private boolean isActive = true;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;

    public AuthUser (UUID id, String email, String password, Role role){
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }

    public void updateEmail (String newEmail){
        this.email = newEmail;
    }
    public void updatePassword(String newPassword){
        this.password = newPassword;
    }
    public void deactivate(){
        this.isActive = false;
        this.deletedAt = LocalDateTime.now();
    }
}
