package com.fauzi.ewallet.user.application.result;

import java.util.UUID;

public record UserResult (UUID authUserId, String fullname, String username, String email) {}
