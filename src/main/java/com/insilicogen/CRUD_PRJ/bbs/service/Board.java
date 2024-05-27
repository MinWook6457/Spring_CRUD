package com.insilicogen.CRUD_PRJ.bbs.service;

import com.insilicogen.CRUD_PRJ.user.service.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BOARD")
public class Board extends BaseEntity {
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

	// 삭제 시기
	@Column(name = "DEL_DT")
	private LocalDateTime deletedAt;

	// 삭제되기 전에 실행되는 메서드
	@PreRemove
	public void preRemove() {
		this.deletedAt = LocalDateTime.now(); // 현재 시간을 삭제 시기로 설정
	}

	@ManyToOne
	@JoinColumn(name = "USER_SN")
	private User user;

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL) // 파일 엔터티와 일대다 관계 설정
	private List<FileEntity> files;

	@Transient
	private int pageNo;

	@Transient
	private int pageUnit;
}
