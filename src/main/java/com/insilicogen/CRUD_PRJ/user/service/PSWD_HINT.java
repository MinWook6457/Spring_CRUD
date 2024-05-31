package com.insilicogen.CRUD_PRJ.user.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "PSWD_HINT")
public class PSWD_HINT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PSWD_HINT_SN")
	private Integer pswdHintSn;
	
	@Column(name = "HINT_CN", length = 300)
	@NotNull
	private String hintCn;

	public PSWD_HINT() {
		//
	}

	public PSWD_HINT(Integer pswdHintSn, String hintCn) {
		this.pswdHintSn = pswdHintSn;
		this.hintCn = hintCn;
	}
}