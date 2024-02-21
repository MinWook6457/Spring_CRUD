package com.insilicogen.CRUD_PRJ.bbs.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insilicogen.CRUD_PRJ.bbs.service.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	Optional<Board> findByboardSn(Integer boardSn);
	
	@Query("SELECT n FROM Board n")
	Page<Board> findPagedBoardList(Pageable pageable);
}

