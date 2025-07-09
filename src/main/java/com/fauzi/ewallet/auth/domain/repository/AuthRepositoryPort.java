package com.fauzi.ewallet.auth.domain.repository;

import java.util.Optional;

import com.fauzi.ewallet.user.domain.User;

public interface AuthRepositoryPort {
    Optional<User> findByEmail(String email);
    void save(User user);
}
