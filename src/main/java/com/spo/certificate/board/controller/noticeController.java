package com.spo.certificate.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class noticeController {

    @GetMapping("/notice")
    public String notice(){

        return "view/notice/notice";
    }

}
