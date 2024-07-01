package com.insilicogen.CRUD_PRJ.security.service;

import com.insilicogen.CRUD_PRJ.security.DTO.UserResponseDTO;
import com.insilicogen.CRUD_PRJ.user.service.User;
import com.insilicogen.CRUD_PRJ.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO getMyInfoBySecurity() {
        return userRepository.findByUserLoginId(SecurityUtil.getCurrentUserId())
                .map(UserResponseDTO::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    @Transactional
    public UserResponseDTO changeUserNickname(String email, String nickname) {
        User user = userRepository.findByUserLoginId(email);

        if(user != null) {
            System.out.println("유저 정보가 없습니다.");
        }
        user.setUserLoginId(nickname);
        return UserResponseDTO.of(userRepository.save(user));
    }

    @Transactional
    public UserResponseDTO changeUserPassword(String email, String exPassword, String newPassword) {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        if (!passwordEncoder.matches(exPassword, user.getPassword())) {
            throw new RuntimeException("비밀번호가 맞지 않습니다");
        }
        user.setPassword(passwordEncoder.encode((newPassword)));
        return UserResponseDTO.of(userRepository.save(user));
    }
}