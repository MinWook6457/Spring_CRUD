package com.insilicogen.CRUD_PRJ.bbs.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board extends BaseEntity{ 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardSn;
	
	@Column(name = "BBS_TTL", length = 255)
	@NotNull
	private String boardTitle;

	@Column(name = "BBS_CN")
	@NotBlank
	@Lob // Clob 설정
	private String boardContent;
	
	@Column(name = "PRIOR_PSTG_YN")
	@NotNull
	private Character priorityPostingOption;
	
	@Column(name = "USE_YN")
	@NotNull
	private Character usingOption;
	
	@Column(name = "DEL_YN")
	@NotNull
	private Character isDeletedOption;
	
	
}
