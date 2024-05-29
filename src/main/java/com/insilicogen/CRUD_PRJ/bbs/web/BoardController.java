package com.insilicogen.CRUD_PRJ.bbs.web;

import java.io.IOException;
import java.time.LocalDateTime;

import com.insilicogen.CRUD_PRJ.bbs.repository.FileRepository;
import com.insilicogen.CRUD_PRJ.bbs.service.BoardService;
import com.insilicogen.CRUD_PRJ.bbs.service.FileEntity;
import com.insilicogen.CRUD_PRJ.bbs.service.FileService;
import com.insilicogen.CRUD_PRJ.bbs.service.dto.FileUploadResultDTO;
import com.insilicogen.CRUD_PRJ.bbs.service.dto.PageRequestDTO;
import com.insilicogen.CRUD_PRJ.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.insilicogen.CRUD_PRJ.bbs.repository.BoardRepository;
import com.insilicogen.CRUD_PRJ.bbs.service.Board;
import com.insilicogen.CRUD_PRJ.bbs.service.dto.BoardUpdatingDTO;
import com.insilicogen.CRUD_PRJ.user.service.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {

	private static final String UPLOAD_DIR = "uploads/";

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;

    @Autowired
    private FileService fileService;

	@GetMapping("/board/createBoard")
	public String createBoard(Model model) {
		return "/board/createBoard";
	}

	@PostMapping("/board/selectBoardList")
	@ResponseBody
	public ResponseEntity<Page<Board>> selectBoardList(@RequestBody PageRequestDTO pageRequestDTO) {
		Page<Board> boardPage = boardService.getBoardPage(pageRequestDTO);
		return ResponseEntity.ok().body(boardPage);
	}



//	@PostMapping("/board/selectBoardList1")
//	@ResponseBody
//	public List<BoardPageNationDTO> selectBoardList1(@RequestBody Board board) {
//		return boardService.getPagedBoard1(String.valueOf(board.getPriorityPostingOption()),getPageRequest(board.getPageNo(),board.getPageUnit()));
//	}
//
	public PageRequest getPageRequest(int page, int size) {
		return PageRequest.of(page - 1, size);
	}

	/*
	*  RequestPart : HTTP request body에 multipart/form-data 가 포함되어 있는 경우에 사용하는 어노테이션
	* */
	@PostMapping("/board/writeBoard")
	@ResponseBody
	public ResponseEntity<String> writeBoard( @RequestParam("boardTitle") String boardTitle,
											  @RequestParam("boardContent") String boardContent,
											  @RequestParam("priorityPostingOption") char priorityPostingOption,
											  @RequestParam("usingOption") String usingOption,
											  @RequestParam("isDeletedOption") char isDeletedOption,
											  @RequestParam("userLoginId") String userLoginId,
											  @RequestParam("file") MultipartFile[] files) { //
		System.out.println("글 작성 컨트롤러 진입");
		try {
			LocalDateTime createdAt = LocalDateTime.now();
			LocalDateTime updatedAt = LocalDateTime.now();

			User user = userService.getUserByLoginId(userLoginId);
			if (user == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user login ID.");
			}

			Board board = new Board();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setPriorityPostingOption(priorityPostingOption);
			board.setUsingOption(usingOption);
			board.setIsDeletedOption(isDeletedOption);
			board.setCreatedAt(createdAt);
			board.setUpdatedAt(updatedAt);
			board.setUser(user);

			List <FileEntity> fileEntities = new ArrayList<>();
			for(MultipartFile file : files){

				FileUploadResultDTO fileUploadResultDTO = new FileUploadResultDTO(
						file.getOriginalFilename(),
						file.getBytes()
				);

				FileEntity fileEntity = new FileEntity();
				fileEntity.setFileName(fileUploadResultDTO.getUploadFileName());
				fileEntity.setFileSize(fileUploadResultDTO.getUploadFileSize());
				fileEntity.setFilePath("path/to/save/" + file.getOriginalFilename());
				fileEntity.setCreatedAt(createdAt);
				fileEntity.setUpdatedAt(updatedAt);
				fileEntity.setBoard(board);

				fileEntities.add(fileEntity);
			}
			boardService.saveBoard(board);
			fileService.saveAllFiles(fileEntities);

			return ResponseEntity.ok("글 작성이 완료되었습니다.");
		} catch (IOException  e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("글 작성 중 오류가 발생했습니다.");
		}
	}

	@GetMapping("/board/read/{boardSn}")
	public String readBoard(@PathVariable Integer boardSn, Model model) {
		Board board = boardService.getBoardById(boardSn);
		model.addAttribute("board", board);
		return "/board/readBoard";
	}

	@DeleteMapping("/board/delete/{boardSn}")
	public ResponseEntity<String> deleteBoard(@PathVariable Integer boardSn, Model model) {
		Board board = boardService.getBoardById(boardSn);
		board.setIsDeletedOption('Y');
		board.setUsingOption("N");
		boardRepository.saveAndFlush(board); // 변경된 엔티티를 즉시 저장
		return ResponseEntity.status(HttpStatus.OK).body("게시글 삭제 완료");
	}

	@GetMapping("/board/updateBoard/{boardSn}")
	public String updateBoard(@PathVariable Integer boardSn, Model model) {
		Board board = boardService.getBoardById(boardSn);
		model.addAttribute("board", board);
		return "/board/updateBoard";
	}

	@PostMapping("/board/updateBoard")
	@ResponseBody
	public ResponseEntity<String> updatingBoard(@RequestBody BoardUpdatingDTO boardUpdatingDTO) {
		try {
			Integer boardSn = Integer.parseInt(boardUpdatingDTO.getBoardSn());
			String boardTitle = boardUpdatingDTO.getBoardTitle();
			String boardContent = boardUpdatingDTO.getBoardContent();
			char priorityPostingOption = boardUpdatingDTO.getPriorityPostingOption();
			String usingOption = boardUpdatingDTO.getUsingOption();
			LocalDateTime updatedAt = LocalDateTime.now();

			Board board = boardService.getBoardById(boardSn);
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setPriorityPostingOption(priorityPostingOption);
			board.setUsingOption(usingOption);
			board.setUpdatedAt(updatedAt); // 수정일자 설정

			boardRepository.saveAndFlush(board); // 변경된 엔티티를 즉시 저장


			return ResponseEntity.ok("글 수정 완료.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("글 수정 중 오류가 발생했습니다.");
		}
	}
}
