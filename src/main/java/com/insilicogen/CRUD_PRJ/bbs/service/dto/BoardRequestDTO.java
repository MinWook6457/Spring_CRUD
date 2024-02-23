package com.insilicogen.CRUD_PRJ.bbs.service.dto;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDTO {
	private String boardTitle;
	private String boardContent;
	private char priorityPostingOption;
	private char usingOption;
	private char isDeletedOption;
	private String userLoginId;

	public BoardRequestDTO(String boardTitle, String boardContent, 
			char priorityPostingOption, char usingOption,
			char isDeletedOption, String userLoginId) {
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.priorityPostingOption = priorityPostingOption;
		this.usingOption = usingOption;
		this.isDeletedOption = isDeletedOption;
		this.userLoginId = userLoginId;
	}
}
