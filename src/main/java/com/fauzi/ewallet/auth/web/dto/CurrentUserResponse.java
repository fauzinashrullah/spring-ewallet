package com.fauzi.ewallet.auth.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrentUserResponse {
    private String fullname;
    private String email;
}
