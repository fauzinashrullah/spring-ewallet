package com.fauzi.ewallet.user.infrastructure.persistence;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String email;
    private String password;
    
}
