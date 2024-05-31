package com.insilicogen.CRUD_PRJ.bbs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.insilicogen.CRUD_PRJ.bbs.service.dto.PageRequestDTO;
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
<<<<<<< HEAD

=======
>>>>>>> 1c0a775e822b43cd9844517d972e1a957e426ba6
		boardRepository.save(board);
	}

	public List<Board> getAllBoards() {
		return boardRepository.findAll();
	}

	public Board getBoardById(Long id) {
		return boardRepository.findByboardSn(id).orElse(null);
	}

	public void deleteById(Long id) {
		Optional<Board> boardOptional = boardRepository.findByboardSn(id);

		if (boardOptional.isPresent()) {
			Board board = boardOptional.get();
			boardRepository.delete(board);
			System.out.println("삭제 완료");
		} else {
			throw new BoardNotFoundException("게시글을 찾을 수 없음");
		}
	}

	public Page<Board> getBoardPage(String usingOption, PageRequestDTO pageRequestDTO) {
		int pageNo = pageRequestDTO.getPageNo();
		int pageSize = pageRequestDTO.getPageSize();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return boardRepository.findByUsingOptionOrderByCreatedAtDesc(usingOption,pageable);
	}


//	public List<BoardPageNationDTO> getPagedBoard1(String opinion, PageRequest pageRequest) {
//		return boardRepository.findUsingOptionAndOrderByPriorityPostingOptionDescCreatedAtDesc(opinion,pageRequest)
//				.stream()
//				.map(BoardPageNationDTO::new)
//				.collect(Collectors.toList());
//	}

	
}
