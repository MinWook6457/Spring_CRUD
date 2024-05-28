package com.insilicogen.CRUD_PRJ.bbs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.insilicogen.CRUD_PRJ.bbs.service.dto.BoardPageNationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insilicogen.CRUD_PRJ.bbs.repository.BoardRepository;

@Service
@Transactional
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void saveBoard(Board board) {
		boardRepository.save(board);
	}

	public List<Board> getAllBoards() {
		return boardRepository.findAll();
	}

	public Board getBoardById(Integer id) {
		return boardRepository.findByboardSn(id).orElse(null);
	}

	public void deleteById(Integer id) {
		Optional<Board> boardOptional = boardRepository.findByboardSn(id);

		if (boardOptional.isPresent()) {
			Board board = boardOptional.get();
			boardRepository.delete(board);
			System.out.println("삭제 완료");
		} else {
			throw new BoardNotFoundException("게시글을 찾을 수 없음");
		}
	}

	public Page<Board> getPagedBoard(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return boardRepository.findUsingOptionYOrderByPriorityAndCreatedAtDesc(pageable);
	}

//	public List<BoardPageNationDTO> getPagedBoard1(String opinion, PageRequest pageRequest) {
//		return boardRepository.findUsingOptionAndOrderByPriorityPostingOptionDescCreatedAtDesc(opinion,pageRequest)
//				.stream()
//				.map(BoardPageNationDTO::new)
//				.collect(Collectors.toList());
//	}

	
}
