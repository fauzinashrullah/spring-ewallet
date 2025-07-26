package com.fauzi.ewallet.user.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fauzi.ewallet.user.domain.model.User;

public interface UsersRepository {
    void save(User user);
    Optional<User> findByAuthUserId(UUID id);
    Optional<User> findByUsername(String username);
    List<User> findAllUsers();
}
