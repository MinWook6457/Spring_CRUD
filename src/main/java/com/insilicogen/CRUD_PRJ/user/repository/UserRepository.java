package com.insilicogen.CRUD_PRJ.user.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insilicogen.CRUD_PRJ.user.service.PSWD_HINT;
import com.insilicogen.CRUD_PRJ.user.service.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserLoginId(String userLoginId); // 로그인 아이디 조회
	
	PSWD_HINT findHintByUserLoginId(String userLoginId);
	
    User findByUserLoginIdAndPassword(String userLoginId, String password);

	// 비밀번호 업데이트를 위한 메서드
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.userLoginId = :userLoginId")
    void updateUserPassword(@Param("userLoginId") String userLoginId, @Param("newPassword") String newPassword);
}
