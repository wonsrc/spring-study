package com.study.springstudy.springmvc.chap04.controller;


import com.study.springstudy.springmvc.chap04.dto.request.ReplyPostRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.response.ReplyDetailResponseDTO;
import com.study.springstudy.springmvc.chap04.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 메서드마다 ResponseBody를 붙일 필요가 없다.
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody ReplyPostRequestDTO dto,
                                    BindingResult result) { // 검증 결과 메세지를 가진 객체.
        if (result.hasErrors()) {
            // 입력값 검증에 걸리면 400번 status 와 함께  메세지를 클라이언트로 전송.
            ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }

        System.out.println("/api/v1/replies: POST");
        System.out.println("dto = " + dto);

        replyService.register(dto);

        return ResponseEntity.ok("success");


    }


    @GetMapping("/{boardNo}")
    public ResponseEntity<?> list(@PathVariable int boardNo) {
        List<ReplyDetailResponseDTO> dtoList = replyService.getList(boardNo);

        return ResponseEntity.ok(dtoList);
    }
}
