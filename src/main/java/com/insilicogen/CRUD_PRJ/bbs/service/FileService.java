package com.insilicogen.CRUD_PRJ.bbs.service;

import com.insilicogen.CRUD_PRJ.bbs.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Transactional
    public void saveAllFiles(List<FileEntity> fileEntities){
        fileRepository.saveAll(fileEntities);
    }
}
