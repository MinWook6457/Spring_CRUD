package com.insilicogen.CRUD_PRJ.user.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "PSWD_HINT")
public class PSWD_HINT {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pswdHintSn;
	
	@Column(name = "HINT_CN", length = 300)
	@NotNull
	private String hintCn;
}
