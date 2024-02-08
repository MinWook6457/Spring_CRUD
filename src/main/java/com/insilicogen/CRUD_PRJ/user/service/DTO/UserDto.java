package com.insilicogen.CRUD_PRJ.user.service.DTO;

import java.time.LocalDateTime;

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
    private LocalDateTime dateOfBirth;
    private String hintAnswer;
}
