package com.insilicogen.CRUD_PRJ.user.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insilicogen.CRUD_PRJ.user.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	//
}