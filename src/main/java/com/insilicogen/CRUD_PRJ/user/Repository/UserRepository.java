package com.insilicogen.CRUD_PRJ.user.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insilicogen.CRUD_PRJ.user.service.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserLoginId(String userLoginId); // 로그인 아이디 조회
}