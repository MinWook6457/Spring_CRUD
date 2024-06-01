package com.insilicogen.CRUD_PRJ.cmt.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDTO {
    private Long boardSn;
    private Long userSn;
    private String userName;
    private String content;

}
