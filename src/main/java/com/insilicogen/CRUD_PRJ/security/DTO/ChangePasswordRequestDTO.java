package com.insilicogen.CRUD_PRJ.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequestDTO {
    private String email;
    private String exPassword;
    private String newPassword;
}