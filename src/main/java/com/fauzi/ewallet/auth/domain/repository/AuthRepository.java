package com.fauzi.ewallet.auth.domain.repository;

import java.util.Optional;
import com.fauzi.ewallet.auth.domain.model.AuthUser;

public interface AuthRepository {
    Optional<AuthUser> findByEmail(String email);
    void save(AuthUser authUser);
}
