package com.fauzi.ewallet.user.application.inbound.dto.result;

import java.util.UUID;

public record AuthResult(UUID userId, String email) {}
