package com.fauzi.ewallet.auth.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fauzi.ewallet.auth.domain.model.AuthUser;

public interface AuthRepository {
    Optional<AuthUser> findByEmail(String email);
    Optional<AuthUser> findById(UUID id);
    void save(AuthUser authUser);
    List<AuthUser> findAllAuth ();
}
