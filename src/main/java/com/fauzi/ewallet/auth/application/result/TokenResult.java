package com.fauzi.ewallet.auth.application.result;

import java.time.Duration;

public record TokenResult(String accessToken, String refreshToken, Duration ttl) {}
