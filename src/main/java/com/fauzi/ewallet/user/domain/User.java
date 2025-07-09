package com.fauzi.ewallet.user.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
}
