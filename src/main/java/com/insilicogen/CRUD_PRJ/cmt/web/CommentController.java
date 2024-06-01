package com.insilicogen.CRUD_PRJ.cmt.web;

import com.insilicogen.CRUD_PRJ.bbs.repository.BoardRepository;
import com.insilicogen.CRUD_PRJ.bbs.service.Board;
import com.insilicogen.CRUD_PRJ.cmt.service.Comment;
import com.insilicogen.CRUD_PRJ.cmt.service.CommentService;
import com.insilicogen.CRUD_PRJ.cmt.service.dto.CommentDTO;
import com.insilicogen.CRUD_PRJ.user.repository.UserRepository;
import com.insilicogen.CRUD_PRJ.user.service.User;
import com.insilicogen.CRUD_PRJ.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Optional;


/*
 * Comment : 공지사항 게시판 댓글 기능 구현
   - 로그인 여부에 상관없이, 공지사항에 댓글 등록이 가능하도록 구현
   - 댓글 수정 / 삭제는 관리자 또는 등록자(비로그인 제외)가 가능하도록 구현(선택)
 * */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;

    // 회원 댓글 등록
    @PostMapping("/comment/createComment")
    @ResponseBody
    public ResponseEntity<String> addComment(@RequestBody CommentDTO commentDto) {
        User user = userRepository.findById(commentDto.getUserSn()).orElse(null);
        Board board = boardRepository.findById(commentDto.getBoardSn()).orElse(null);

        if (user == null || board == null) {
            return ResponseEntity.badRequest().body("Invalid user or board ID");
        }

        System.out.println(user);
        System.out.println(board);

        String commentContent = commentDto.getContent();
        LocalDateTime createdAt = LocalDateTime.now();

        Comment comment = new Comment();
        comment.setCommentContent(commentContent);
        comment.setCreatedAt(createdAt);
        comment.setUser(user);
        comment.setBoard(board);
        comment.setIsDeletedOption('N'); // 기본 옵션 설정

        commentService.saveComment(comment);

        return ResponseEntity.ok("댓글 작성 완료");
    }
}
