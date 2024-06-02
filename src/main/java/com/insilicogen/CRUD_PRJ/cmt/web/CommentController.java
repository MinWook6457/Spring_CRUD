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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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

        Board board = boardRepository.findById(commentDto.getBoardSn()).orElse(null);
        if (board == null) {
            return ResponseEntity.badRequest().body("Invalid board ID");
        }


        User user = board.getUser();
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid user ID");
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

    // 회원 댓글 조회
    @GetMapping("/comment/readComment/{commentSn}")
    public ResponseEntity<?> readComment(@PathVariable Long commentSn) { // <?> 를 통해 어느 타입이든 반환 가능하게 조정
        Comment comment = commentService.getCommentById(commentSn);
        if(comment==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글이 없습니다.");
        }

        Board board = comment.getBoard();
        if(board==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글에 해당되는 게시글이 존재하지 않음!");
        }

        List<Comment> comments = commentService.getAllCommentsByBoardId(board.getBoardSn());


        return ResponseEntity.ok(comments);
    }
}
