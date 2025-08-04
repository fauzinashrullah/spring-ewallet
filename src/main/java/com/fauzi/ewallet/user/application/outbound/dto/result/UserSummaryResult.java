package com.fauzi.ewallet.user.application.outbound.dto.result;

import java.util.UUID;

public record UserSummaryResult (UUID authUserId, String fullname, String username, String phoneNumber, String email) {}
