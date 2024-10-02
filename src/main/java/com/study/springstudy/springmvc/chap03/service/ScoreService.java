package com.study.springstudy.springmvc.chap03.service;

/*
    컨트롤러와 레파지토리 사이에 위치하여
    중간 처리를 담당

    - 트랜잭션 처리, 데이터 가공 처리...

    - 의존 관계
    Controller -> Service -> repository

 */

import com.study.springstudy.springmvc.chap03.dto.ScoreResponseDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.repository.ScoreJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreJdbcRepository repository;

    public List<Score> findAll(String sort) {
        /*
        컨트롤러는 데이터베이스에서 성적정보 리스트를
        조회해 오기를 원하고 있다.
        그런데 데이터베이스는 민감한 정보까지 모두 조회한다.
        그리고 컬럼명도 그대로 노출하기 때문에
        이 모든것을 숨기는 처리를 하고 싶다. -> response용 DTO를 생성하자!
         */

//        List<ScoreResponseDTO> dtoList = new ArrayList<>();
//        for (Score score : scoreList) {
//            ScoreResponseDTO dto = new ScoreResponseDTO(score);
//            dtoList.add(dto);
//        }
        List<ScoreResponseDTO> dtoList = scoreList
                .stream()
                .map(ScoreResponseDTO::new)
                .collect(Collectors.toList());
    }
}
