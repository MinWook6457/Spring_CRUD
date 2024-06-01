package com.insilicogen.CRUD_PRJ.cmt.repository;

import com.insilicogen.CRUD_PRJ.cmt.service.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA Repo는 DAO느낌으로 ORM 기능 사용 가능
public interface CommentRepository extends JpaRepository<Comment,Long> {
    // 
}
