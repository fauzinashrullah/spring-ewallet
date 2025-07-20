package com.fauzi.ewallet.auth.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePasswordRequest {
    //Validasi
    private String oldPassword;
    private String newPassword;
}
