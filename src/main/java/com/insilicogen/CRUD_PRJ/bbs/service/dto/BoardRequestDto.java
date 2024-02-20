package com.insilicogen.CRUD_PRJ.bbs.service.dto;

import java.time.LocalDateTime;

import com.insilicogen.CRUD_PRJ.user.service.dto.UserDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
	private String boardTitle;
	private String boardContent;
	private char priorityPostingOption;
	private char usingOption;
	private char isDeletedOption;
	private String userLoginId;

	public BoardRequestDto(String boardTitle, String boardContent, 
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
