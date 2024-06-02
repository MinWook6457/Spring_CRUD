package com.insilicogen.CRUD_PRJ.cmt.service;

import com.insilicogen.CRUD_PRJ.bbs.repository.BoardRepository;
import com.insilicogen.CRUD_PRJ.bbs.service.Board;
import com.insilicogen.CRUD_PRJ.cmt.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment getCommentById(Long commentSn) {
        return commentRepository.findById(commentSn).orElse(null);
    }

    // 게시글 ID로 모든 댓글 조회
    public List<Comment> getAllCommentsByBoardId(Long boardSn) {
        return commentRepository.findAllByBoardBoardSn(boardSn);
    }

}
