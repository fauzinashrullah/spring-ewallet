package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fauzi.ewallet.auth.domain.model.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "auth_user")
public class AuthEntity {
    @Id
    private UUID id;
    private String email;
    private String password;
    private Role role;
    private boolean isActive = true;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
}
