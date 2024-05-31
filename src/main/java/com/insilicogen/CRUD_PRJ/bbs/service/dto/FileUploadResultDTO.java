package com.insilicogen.CRUD_PRJ.bbs.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileUploadResultDTO { // 파일 업로드 시 사용될 DTO
    private String uploadFileName;
    private byte[] uploadFileSize;

    public String getUploadFileName() {
        return uploadFileName;
    }

    public byte[] getUploadFileSize(){
        return uploadFileSize;
    }
}
