package com.insilicogen.CRUD_PRJ.user.service.VO;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
    private Integer userSn;
    private String userLoginId;
    private String userNm;
    private String password;
    private Character sex;
    private Date dateOfBirth;
    private String hintAnswer;
    private Integer pswdHintSn;
}
