package com.insilicogen.CRUD_PRJ.bbs.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BBS_ATCH_FILE")
public class FileEntity extends BaseEntity {
    /* 파일 테이블 기본 키 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileSn;

    /* 파일 명 */
    @Column(name = "ATCH_FILE_NM", length = 255)
    @NotNull
    private String fileName;

    /* 파일 크기 */
    @Column(name = "ATCH_FILE_SZ", columnDefinition = "bytea")
    @NotNull
    private byte[] fileSize;

    @Column(name = "ATCH_FILE_PATH", length = 255)
    @NotNull
    private String filePath;

    @Column(name = "DEL_DT")
    private LocalDateTime deletedAt;

    // 삭제 되기 전에 실행 되는 메서드
    @PreRemove
    public void preRemove() {
        this.deletedAt = LocalDateTime.now(); // 현재 시간을 삭제 시기로 설정
    }

    @ManyToOne // 지연 로딩을 통해 게시판 조회 시 순환 참조 방지
    @JoinColumn(name = "BOARD_SN")
    private Board board;
}
