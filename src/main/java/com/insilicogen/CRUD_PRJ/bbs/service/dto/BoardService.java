package com.insilicogen.CRUD_PRJ.bbs.service.dto;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insilicogen.CRUD_PRJ.bbs.repository.BoardRepository;
import com.insilicogen.CRUD_PRJ.bbs.service.Board;

@Service
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
}
