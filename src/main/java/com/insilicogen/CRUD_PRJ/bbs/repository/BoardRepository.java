package com.insilicogen.CRUD_PRJ.bbs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insilicogen.CRUD_PRJ.bbs.service.Board;

// DAO => CRUD 기능 사용
public interface BoardRepository extends JpaRepository<Board, Long> {
	@EntityGraph(attributePaths = {"files"})
	Optional<Board> findByboardSn(Long boardSn);

//	@Query("SELECT b FROM Board b WHERE b.usingOption = 'Y' " + "ORDER BY b.priorityPostingOption DESC,"
//			+ "b.createdAt DESC")
//	Page<BoardPageResponseDTO> findUsingOptionYOrderByPriorityAndCreatedAtDesc(Pageable pageable);

//	@EntityGraph(attributePaths = {"files"})
//	@Query("SELECT b FROM Board b WHERE b.usingOption = 'Y' ORDER BY b.priorityPostingOption DESC, b.createdAt DESC")
//	Page<Board> findByUsingOptionOrderByPriorityPostingOptionDescCreatedAtDesc(Pageable pageable);

//	List<Board> findByUsingOptionOrderByPriorityPostingOptionDescCreatedAtDesc(String usingOption, Pageable pageable);

//	List<Board> findUsingOptionAndOrderByPriorityPostingOptionDescCreatedAtDesc(
//			String usingOption, Pageable pageable);

	Page<Board> findByUsingOptionOrderByPriorityPostingOptionDescCreatedAtDesc(String usingOption, Pageable pageable);
}
