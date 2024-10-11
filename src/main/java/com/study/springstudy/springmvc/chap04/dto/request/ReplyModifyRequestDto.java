package com.study.springstudy.springmvc.chap04.dto.request;

import com.study.springstudy.springmvc.chap04.entity.Reply;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@Builder
public class ReplyModifyRequestDto {
    private int rno;

    @NotBlank
    private String text;

    // dto -> entity 로 바꾸는 메서드
    public Reply toEntity() {
        return Reply.builder()
                .replyNo(rno)
                .replyText(text)
                .build();
    }

}
