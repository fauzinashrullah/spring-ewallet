package com.fauzi.ewallet.auth.infrastructure.security;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fauzi.ewallet.auth.domain.model.Role;
import com.fauzi.ewallet.auth.infrastructure.persistence.AuthEntity;

import lombok.Getter;

@Getter
public class UserDetailsImpl implements UserDetails {
    private final UUID id;
    private final String email;
    private final String password;
    private final Role role;

    public UserDetailsImpl(AuthEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override public String getUsername() { return email; }
}
