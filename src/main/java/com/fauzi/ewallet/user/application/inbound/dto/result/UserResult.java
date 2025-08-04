package com.fauzi.ewallet.user.application.inbound.dto.result;

import java.util.UUID;

public record UserResult (UUID authUserId, String fullname, String username, String phoneNumber, String email) {}
