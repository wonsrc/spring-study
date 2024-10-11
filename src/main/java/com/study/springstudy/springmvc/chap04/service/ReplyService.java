package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.PageDTO;
import com.study.springstudy.springmvc.chap04.dto.request.ReplyModifyRequestDto;
import com.study.springstudy.springmvc.chap04.dto.request.ReplyPostRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.response.ReplyDetailResponseDTO;
import com.study.springstudy.springmvc.chap04.dto.response.ReplyListResponseDTO;
import com.study.springstudy.springmvc.chap04.entity.Reply;
import com.study.springstudy.springmvc.chap04.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper mapper;

    public void delete(int replyNo) throws Exception {
        mapper.delete(replyNo);
    }

    public void register(ReplyPostRequestDTO dto) {
        mapper.save(dto.toEntity());
    }

    public ReplyListResponseDTO getList(int boardNo, int pageNo) {
        PageDTO page = new PageDTO();
        page.setAmount(5);
        page.setPageNo(pageNo);

        List<Reply> replyList = mapper.findAll(boardNo, page);

        List<ReplyDetailResponseDTO> dtoList = new ArrayList<>();
        replyList.forEach(reply -> dtoList.add(new ReplyDetailResponseDTO(reply)));

        return ReplyListResponseDTO.builder()
                .count(dtoList.size())
                .pageInfo(new PageMaker(page, mapper.count(boardNo)))
                .replies(dtoList)
                .build();

    }

    public void modify(ReplyModifyRequestDto dto) {
        mapper.modify(dto.toEntity());
    }
}