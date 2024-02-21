package com.insilicogen.CRUD_PRJ.bbs.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdatingDTO {
	private String boardSn;
	private String boardTitle;
	private String boardContent;
	private char priorityPostingOption;
	private char usingOption;
	private String userLoginId;

	public BoardUpdatingDTO(String boardSn,String boardTitle, String boardContent, 
			char priorityPostingOption, char usingOption,
			 String userLoginId) {
		this.boardSn = boardSn;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.priorityPostingOption = priorityPostingOption;
		this.usingOption = usingOption;
		this.userLoginId = userLoginId;
	}
}
