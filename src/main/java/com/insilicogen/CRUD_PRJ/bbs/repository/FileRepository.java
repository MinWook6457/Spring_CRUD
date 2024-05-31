package com.insilicogen.CRUD_PRJ.bbs.repository;

import com.insilicogen.CRUD_PRJ.bbs.service.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
   // Optional<File> findByFileSn(Integer fileSn);
}
