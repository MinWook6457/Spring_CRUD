package com.insilicogen.CRUD_PRJ.bbs.service.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileUploadResultDTO { // 파일 업로드 시 사용될 DTO
    private String uploadFileName;
    private String uploadFilePath;

    public String getUploadFileName() {
        return uploadFileName;
    }

    public String getUploadFilePath(){
        return uploadFilePath;
    }
}
