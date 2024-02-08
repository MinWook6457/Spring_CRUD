package com.insilicogen.CRUD_PRJ.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insilicogen.CRUD_PRJ.user.repository.UserRepository;
import com.insilicogen.CRUD_PRJ.user.service.DTO.UserDto;

@Service
public class UserRegisterService {

    private final UserRepository userRepository;

    @Autowired
    public UserRegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDto userDto) {
        // UserDto 객체를 User 엔티티로 변환
        User user = new User();
        user.setUserLoginId(userDto.getUserLoginId());
        user.setUserNm(userDto.getUserNm());
        user.setPassword(userDto.getPassword());
        user.setSex(userDto.getSex());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setHintAnswer(userDto.getHintAnswer());

        // UserRepository를 사용하여 User 엔티티를 저장
        userRepository.save(user);
        
        System.out.println("유저 정보 db 저장 완료");
    }
}
