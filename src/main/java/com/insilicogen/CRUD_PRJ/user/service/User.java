package com.insilicogen.CRUD_PRJ.user.service;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.insilicogen.CRUD_PRJ.bbs.service.Board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size; // Hibernate Validator 인터페이스 : jakarta
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "User_TBL")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSn;

    @Column(name = "USER_LGN_ID", length = 30)
    @NotNull
    private String userLoginId;

    @Column(name = "USER_NM", length = 30)
    @NotNull
    private String userNm;

    @Column(name = "PSWD", length = 30)
    @NotNull
    @NotBlank
    @Size(min=8)
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
  

}