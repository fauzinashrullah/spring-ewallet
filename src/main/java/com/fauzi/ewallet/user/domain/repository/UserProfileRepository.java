package com.fauzi.ewallet.user.domain.repository;

import java.util.UUID;

import com.fauzi.ewallet.user.domain.model.User;

public interface UserProfileRepository {
    void save(User user);
    User findByAuthUserId(UUID id);
}
