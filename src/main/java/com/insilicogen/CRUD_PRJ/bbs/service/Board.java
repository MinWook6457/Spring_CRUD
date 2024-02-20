package com.insilicogen.CRUD_PRJ.bbs.service;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.insilicogen.CRUD_PRJ.user.service.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BOARD")
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
}
