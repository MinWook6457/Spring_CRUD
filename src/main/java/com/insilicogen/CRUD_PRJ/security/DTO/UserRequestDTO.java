package com.insilicogen.CRUD_PRJ.security.DTO;

import com.insilicogen.CRUD_PRJ.security.role.UserRole;
import com.insilicogen.CRUD_PRJ.user.service.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    private String email;
    private String password;
    private String nickname;
    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userLoginId(email)
                .password(passwordEncoder.encode(password))
                .userNm(nickname)
                .userRole(UserRole.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}