package com.insilicogen.CRUD_PRJ.bbs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insilicogen.CRUD_PRJ.bbs.service.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	Optional<Board> findByboardSn(Integer boardSn);
}

