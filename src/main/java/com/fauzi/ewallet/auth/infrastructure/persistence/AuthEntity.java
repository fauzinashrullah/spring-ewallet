package com.fauzi.ewallet.auth.infrastructure.persistence;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "auth_user")
public class AuthEntity {
    @Id
    private UUID id;
    private String email;
    private String password;
}
