package com.study.springstudy.springmvc.chap03.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 역할 : 부라우저가 요청과 함께 전달한 성적 정보를 포장하는 객체
@Getter @Setter
@ToString
public class ScorePostDTO {

    private String name;
    private int kor;
    private int eng;
    private int math;
}
