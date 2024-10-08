package com.study.springstudy.springmvc.chap04.entity;

import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Board {

    private int boardNo;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime regDate;
    private String writer;

    public Board(BoardWriteRequestDTO dto) {
        this.writer = dto.getWriter();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
