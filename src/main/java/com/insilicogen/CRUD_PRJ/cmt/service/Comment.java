package com.insilicogen.CRUD_PRJ.cmt.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.insilicogen.CRUD_PRJ.bbs.service.BaseEntity;
import com.insilicogen.CRUD_PRJ.bbs.service.Board;
import com.insilicogen.CRUD_PRJ.user.service.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CMNT")
public class Comment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentSn;
	
	@Column(name = "CMNT_CN")
	@NotBlank
	@Lob // Clob 설정
	private String commentContent; // 댓글 내용
	
	@Column(name = "DEL_YN") // 삭제 여부
	@NotNull
	private Character isDeletedOption;
	
    @ManyToOne
    @JoinColumn(name = "BOARD_SN")
    private Board board;
	
    @ManyToOne
    @JoinColumn(name = "USER_SN")
    private User user;
}
