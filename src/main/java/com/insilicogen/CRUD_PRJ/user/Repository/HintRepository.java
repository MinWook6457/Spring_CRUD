package com.insilicogen.CRUD_PRJ.user.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insilicogen.CRUD_PRJ.user.service.PSWD_HINT;

@Repository
public interface HintRepository extends JpaRepository<PSWD_HINT, Long>{
	@Query("SELECT n FROM PSWD_HINT n")
    List<PSWD_HINT> findAll();
}