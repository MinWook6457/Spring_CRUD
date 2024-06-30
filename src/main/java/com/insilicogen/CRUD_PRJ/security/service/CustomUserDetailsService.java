package com.insilicogen.CRUD_PRJ.security.service;

import com.insilicogen.CRUD_PRJ.security.DTO.*;
import com.insilicogen.CRUD_PRJ.user.*;
import com.insilicogen.CRUD_PRJ.security.jwt.*;
import com.insilicogen.CRUD_PRJ.user.repository.UserRepository;
import com.insilicogen.CRUD_PRJ.user.service.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByUserNm(username)
                .map(this::createUserDetails)
                .orElseThrow(()->new UsernameNotFoundException(username + "DB에서 찾을 수 없음"));


    }

    private UserDetails createUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getUserRole().toString());

        return new User( // 엔터티 이름이 겹쳐 오류 발생
                String.valueOf(user.getUserSn()),
                user.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
