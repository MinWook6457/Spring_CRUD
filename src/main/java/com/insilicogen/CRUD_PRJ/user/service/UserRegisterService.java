package com.insilicogen.CRUD_PRJ.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insilicogen.CRUD_PRJ.user.repository.UserRepository;
import com.insilicogen.CRUD_PRJ.user.service.DTO.UserDto;

@Service
public class UserRegisterService {

	@Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserRegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(UserDto userDto) {
        // UserDto 객체를 User 엔티티로 변환
        User user = new User();
        
        System.out.println("유저 로그인 아이디 : " + userDto.getUserLoginId());
        System.out.println("유저 이름 : " + userDto.getUserNm());
        System.out.println("유저 비밀번호 : " + userDto.getPassword());
        System.out.println("유저 성별 : " + userDto.getSex());
        System.out.println("유저 생일 : " + userDto.getDateOfBirth());
        System.out.println("유저 비밀번호 힌트 : " + userDto.getHintAnswer());       
        
        user.setUserLoginId(userDto.getUserLoginId());
        user.setUserNm(userDto.getUserNm());
        user.setPassword(userDto.getPassword());
        user.setSex(userDto.getSex());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setHintAnswer(userDto.getHintAnswer());

        // UserRepository를 사용하여 User 엔티티를 저장
        this.userRepository.save(user);
        
        System.out.println("유저 정보 db 저장 완료");
        return user;
    }
}
