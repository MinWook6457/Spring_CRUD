package com.insilicogen.CRUD_PRJ.cmt.service;

import com.insilicogen.CRUD_PRJ.cmt.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
