package com.insilicogen.CRUD_PRJ.user.service;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.insilicogen.CRUD_PRJ.bbs.service.BaseEntity;
import com.insilicogen.CRUD_PRJ.bbs.service.Board;

import com.insilicogen.CRUD_PRJ.security.role.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size; // Hibernate Validator 인터페이스 : jakarta
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "User_TBL")
@NoArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSn;

    @Column(name = "USER_LGN_ID", length = 30)
    @NotNull
    private String userLoginId;

    @Column(name = "USER_NM", length = 30)
    @NotNull
    private String userNm;

    @Column(name = "PSWD", length = 30)
    @NotNull
    @NotBlank
    @Size(min = 8)
    private String password;

    @Column(name = "SEXDSTN", length = 1)
    private Character sex;

    @Column(name = "BRTH_YMD")
    private Date dateOfBirth;

    @Column(name = "HINT_CNSR", length = 150)
    @NotNull
    private String hintAnswer;

    @ManyToOne
    @JoinColumn(name = "PSWD_HINT_SN")
    private PSWD_HINT pswdHintSn;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    public User(Long userSn, String userLoginId, String userNm, String password,
                Character sex, Date dateOfBirth, String hintAnswer, UserRole userRole) {
        this.userSn = userSn;
        this.userLoginId = userLoginId;
        this.userNm = userNm;
        this.password = password;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.hintAnswer = hintAnswer;
        this.userRole = userRole;
    }

}