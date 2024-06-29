package com.insilicogen.CRUD_PRJ.security.DTO;

import hello.hellospring.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDTO {
    private String email;
    private String nickname;
    public static MemberResponseDTO of(Member member) {
        return MemberResponseDTO.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
