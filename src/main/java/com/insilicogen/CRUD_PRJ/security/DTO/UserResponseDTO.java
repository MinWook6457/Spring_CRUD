package com.insilicogen.CRUD_PRJ.security.DTO;

import com.insilicogen.CRUD_PRJ.user.service.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private String email;
    private String nickname;
    public static UserResponseDTO of(User user) {
        return UserResponseDTO.builder()
                .email(user.getUserLoginId())
                .nickname(user.getUserNm())
                .build();
    }
}
