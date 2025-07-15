package com.fauzi.ewallet.auth.domain.repository;

public interface PasswordHasher {
    String hash(String password);
    boolean verify(String raw, String hashed);
}
