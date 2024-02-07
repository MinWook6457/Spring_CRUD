package com.insilicogen.CRUD_PRJ.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PSWD_HINT")
public class PSWD_HINT {
	@Id
	@Column(name = "PSWD_HINT_SN") // 유저 순번(유저 테이블 고유 id)
	private Integer pswdHintSn;
}
