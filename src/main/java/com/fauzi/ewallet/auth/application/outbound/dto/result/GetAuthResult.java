package com.fauzi.ewallet.auth.application.outbound.dto.result;

import java.util.UUID;

public record GetAuthResult(UUID id, String email) {}
