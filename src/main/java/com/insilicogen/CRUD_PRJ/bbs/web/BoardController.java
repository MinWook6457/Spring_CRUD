package com.insilicogen.CRUD_PRJ.bbs.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insilicogen.CRUD_PRJ.bbs.repository.BoardRepository;
import com.insilicogen.CRUD_PRJ.bbs.service.Board;
import com.insilicogen.CRUD_PRJ.bbs.service.dto.BoardRequestDto;
import com.insilicogen.CRUD_PRJ.bbs.service.dto.BoardService;
import com.insilicogen.CRUD_PRJ.user.service.User;
import com.insilicogen.CRUD_PRJ.user.service.UserService;

@Controller
public class BoardController {
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;

	@GetMapping("/board/createBoard")
	public String createBoard(Model model) { // 홈 페이지
		return "/board/createBoard";
	}

	@PostMapping("/board/writeBoard")
	@ResponseBody
	public ResponseEntity<String> writeBoard(@RequestBody BoardRequestDto boardRequestDto) {
		System.out.println("글 작성 컨트롤러 진입");
		try {

			String boardTitle = boardRequestDto.getBoardTitle();
			String boardContent = boardRequestDto.getBoardContent();
			char priorityPostingOption = boardRequestDto.getPriorityPostingOption();
			char usingOption = boardRequestDto.getUsingOption();
			char isDeletedOption = boardRequestDto.getIsDeletedOption();
			LocalDateTime createdAt = LocalDateTime.now(); // 현재 시간으로
			LocalDateTime updatedAt = LocalDateTime.now(); // 현재 시간으로
			LocalDateTime deletedAt = null; // 삭제 시간은 null로 설정

			Board board = new Board();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setPriorityPostingOption(priorityPostingOption);
			board.setUsingOption(usingOption);
			board.setIsDeletedOption(isDeletedOption);
			board.setCreatedAt(createdAt); // 생성일자 설정
			board.setUpdatedAt(updatedAt); // 수정일자 설정
			board.setDeletedAt(deletedAt);

			String userLoginId = boardRequestDto.getUserLoginId();

			User user = userService.getUserByLoginId(userLoginId);

			System.out.println(user.getUserNm());

			board.setUser(user);

			boardService.saveBoard(board);

			return ResponseEntity.ok("글 작성이 완료되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("글 작성 중 오류가 발생했습니다.");

		}
	}

	@GetMapping("/board/read/{boardSn}")
	public String readBoard(@PathVariable Integer boardSn, Model model) {
		Board board = boardService.getBoardById(boardSn);
		
		model.addAttribute("board", board);
		
		return "board/readBoard";
	}
}
