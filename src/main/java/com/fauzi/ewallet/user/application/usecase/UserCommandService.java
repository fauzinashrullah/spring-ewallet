package com.fauzi.ewallet.user.application.usecase;

import java.util.UUID;

public interface UserCommandService {
    void createProfile(UUID authUserId, String fullname, String username, String phoneNumber);
}
