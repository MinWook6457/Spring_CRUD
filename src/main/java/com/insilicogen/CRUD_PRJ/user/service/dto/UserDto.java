package com.insilicogen.CRUD_PRJ.user.service.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UserDto {
    private String userLoginId;
    private String userNm;
    private String password;
    private Character sex;
    private Date dateOfBirth;
    private String hintAnswer;
}