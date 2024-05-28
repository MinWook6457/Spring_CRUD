package com.insilicogen.CRUD_PRJ.bbs.repository;

import java.util.List;
import java.util.Optional;

import com.insilicogen.CRUD_PRJ.bbs.service.dao.BoardPageNationDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insilicogen.CRUD_PRJ.bbs.service.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
	Optional<Board> findByboardSn(Integer boardSn);

	@Query("SELECT b FROM Board b WHERE b.usingOption = 'Y' " + "ORDER BY b.priorityPostingOption DESC,"
			+ "b.createdAt DESC")
	Page<BoardPageNationDAO> findUsingOptionYOrderByPriorityAndCreatedAtDesc(Pageable pageable);

//	List<Board> findUsingOptionAndOrderByPriorityPostingOptionDescCreatedAtDesc(
//			String usingOption, Pageable pageable);
}
