package com.fauzi.ewallet.user.application.inbound.dto.result;

import java.util.UUID;

public record UpdateUserResult(UUID userId, String fullname) {}
