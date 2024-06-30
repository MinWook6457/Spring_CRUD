package com.insilicogen.CRUD_PRJ.user.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insilicogen.CRUD_PRJ.user.service.PSWD_HINT;
import com.insilicogen.CRUD_PRJ.user.service.User;

import java.util.List;
import java.util.Optional;

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

    // 남성 유저 조회
    @Query("select count(u) from User u where u.sex = 'M'")
    long countMaleUsers();

    // 여성 유저 조회
    @Query("select count(u) from User u where u.sex = 'F'")
    long countFemaleUsers();

    // 유저 나이
    @Query("SELECT YEAR(CURRENT_DATE()) - YEAR(u.dateOfBirth) FROM User u")
    List<Integer> getUsersAge();

    List<User> findAllByOrderByCreatedAtDesc();

    boolean existsByUserLoginId(String userLoginId);

    Optional<User> findByUserNm(String userNm);
}
