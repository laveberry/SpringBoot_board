package com.springboot.board.dto;

import com.springboot.board.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

/**
 DTO : Controller와 Service 사이에 데이터 교환.
        DTO를 통해 savePost에서 Repository 데이터 집어넣음
 * */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    //dto에서 필요한 부분을 빌더패턴을 통해 entity로 만듬
    public Board toEntity(){
        Board build = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder //setter 대신 사용
    public BoardDTO(Long id, String writer, String title, String content, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }
}
