package com.fauzi.ewallet.user.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequest {
    //validasi 
    private String name;
    private String email;
}
