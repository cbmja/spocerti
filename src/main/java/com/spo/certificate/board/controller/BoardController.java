package com.spo.certificate.board.controller;

import com.spo.certificate.board.dto.BoardData;
import com.spo.certificate.board.service.BoardDataSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardDataSaveService boardDataSaveService;

    @GetMapping("/board/board")
    public String board(){

        return "view/board/board";
    }

    @PostMapping("/board/save")
    public ResponseEntity<String> saveBoard(@RequestBody BoardData boardData) {
        boardDataSaveService.save(boardData);
        return ResponseEntity.ok("글이 저장되었습니다.");
    }

}
