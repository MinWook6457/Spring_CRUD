package com.insilicogen.CRUD_PRJ.user;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "USER")
public class User {

	@Id
	@Column(name = "USER_SN") // 유저 순번(유저 테이블 고유 id)
	private Integer userSn;
	
	@Column(name = "USER_LGN_ID", length = 30)
	private String userLoginId;

	@Column(name = "USER_NM", length = 30)
	private String userNm;

	@Column(name = "PSWD", length = 30)
	private String password;
	
	@Column(name = "SEXDSTN", length = 1) // 성별
	private char sex;
	
	@Column(name = "BRTH_YMD", length = 1) // 성별
	private Date date;	 
	
	@Column(name = "HINT_CNSR", length = 150) // 성별
	private String hintAnswer;
	
	@OneToMany
	@JoinColumn(name = "PSWD_HINT_SN")
	private PSWD_HINT pswd_hint;
}
