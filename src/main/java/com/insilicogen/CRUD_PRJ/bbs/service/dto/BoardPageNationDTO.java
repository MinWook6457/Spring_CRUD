package com.insilicogen.CRUD_PRJ.bbs.service.dto;

import com.insilicogen.CRUD_PRJ.bbs.service.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardPageNationDTO {

    private String title;

    public BoardPageNationDTO(Board board) {
        this.title=board.getBoardTitle();
    }
}
