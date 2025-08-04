package com.fauzi.ewallet.auth.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fauzi.ewallet.auth.domain.exception.RedundantUpdateException;
import com.fauzi.ewallet.auth.domain.exception.InactiveUserException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUser {
    private UUID id;
    private String email;
    private String password;
    private Role role;
    private boolean isActive;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;

    public AuthUser (UUID id, String email, String password, Role role){
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
    }

    public void updateEmail (String newEmail){
        if (this.email.equals(newEmail)) throw new RedundantUpdateException("Email must be different from the current one.");
        this.email = newEmail;
    }
    public void updatePassword(String newPassword){
        this.password = newPassword;
    }
    public void deactivate(){
        if (!this.isActive) throw new InactiveUserException("User not found");
        this.isActive = false;
        this.deletedAt = LocalDateTime.now();
    }
}
