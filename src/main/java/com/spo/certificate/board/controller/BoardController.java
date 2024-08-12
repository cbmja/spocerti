package com.spo.certificate.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/board/board")
    public String board(){

        return "view/board/board";
    }

}
