package com.insilicogen.CRUD_PRJ.bbs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insilicogen.CRUD_PRJ.bbs.service.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
	Optional<Board> findByboardSn(Integer boardSn);

//	@Query("SELECT b FROM Board b WHERE b.usingOption = 'Y' " + "ORDER BY b.priorityPostingOption DESC,"
//			+ "b.createdAt DESC")
//	Page<BoardPageResponseDTO> findUsingOptionYOrderByPriorityAndCreatedAtDesc(Pageable pageable);

//	@Query("SELECT b FROM Board b LEFT JOIN FETCH b.files WHERE b.usingOption = 'Y' ORDER BY b.priorityPostingOption DESC, b.createdAt DESC")
//	Page<Board> findByUsingOptionOrderByPriorityPostingOptionDescCreatedAtDesc(Pageable pageable);

//	List<Board> findByUsingOptionOrderByPriorityPostingOptionDescCreatedAtDesc(String usingOption, Pageable pageable);

//	List<Board> findUsingOptionAndOrderByPriorityPostingOptionDescCreatedAtDesc(
//			String usingOption, Pageable pageable);

	Page<Board> findAll(Pageable pageable);
}
