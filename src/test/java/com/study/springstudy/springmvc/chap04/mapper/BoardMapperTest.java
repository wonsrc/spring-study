package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.dto.request.PageDTO;
import com.study.springstudy.springmvc.chap04.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
//@Rollback
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    // 테스트 전에 실행할 코드
    @Test
    void bulkInsert() {
        for (int i = 1; i <= 300; i++) {
            Board b = new Board();
            b.setTitle("테스트제목" + i);
            b.setWriter("글쓴이" + i);
            b.setContent("내용" + i);

            mapper.save(b);
        }

    }

    @Test
    @DisplayName("페이징이 적용된 게시물 조회 테스트")
    void findAllTest() {
        // given
        PageDTO page = new PageDTO();// pageNo : 1, amount 6
        page.setPageNo(16);
        page.setAmount(10);

        // when
        List<Board> boardList = mapper.findAll(page);


        // then
        boardList.forEach(System.out::println);
        assertEquals(10,boardList.size());
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