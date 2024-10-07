package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.entity.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    // 테스트 전에 실행할 코드
    @Test
    void bulkInserrt() {
        for (int i = 1; i <= 10; i++) {
            Board b = new Board();
            b.setTitle("테스트제목" + i);
            b.setWriter("글쓴이" + i);
            b.setContent("내용" + i);

            mapper.save(b);
        }

    }

    @Test
    @DisplayName("전체조회 테스트를 수행하면 게시물의 수가 3개일 것이다.")
    void findAllTest() {
        // given

        // when
        List<Board> boardList = mapper.findAll();


        // then
        boardList.forEach(System.out::println);
        assertEquals(3,boardList.size());
    }

    @Test
    @DisplayName("3번 글을 상세 조회했을 때 작성자가 글쓴이 3일 것이다.")
    void findOneTest() {
        // given
        int boardNo = 6;

        // when
        Board board = mapper.findOne(boardNo);

        // then
        assertEquals("글쓴이3", board.getWriter());
    }

    @Test
    @DisplayName("14번 글의 조회수를 2번 증가시킨 후 확인해 보면 조회수가 2일 것이다.")
    void updateViewTest() {
        // given
        int boardNo = 14;

        // when
        mapper.updateViewCount(boardNo);
        mapper.updateViewCount(boardNo);
        // then
        assertEquals(2, mapper.findOne(14).getViewCount());
    }

    @Test
    @DisplayName("19번 글을 삭제하면 ")
    void deleteTest() {
        // given
        int boardNo = 19;
        // when
        mapper.delete(boardNo);
        // then
        assertNull(mapper.findOne(boardNo));
    }
}