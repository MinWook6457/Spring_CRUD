package com.insilicogen.CRUD_PRJ.bbs.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageRequestDTO {
    // 번호	제목 	작성자	등록일
    private int pageNo;
    private int pageSize;

    public PageRequestDTO(int pageNo, int pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
