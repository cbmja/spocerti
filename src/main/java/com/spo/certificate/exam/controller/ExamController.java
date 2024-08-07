package com.spo.certificate.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExamController {

    //시험 검색 - ajax
    @GetMapping("/exam")
    public String findExam(){

        return "view/main/main";
    }

    //시험 과목 검색 - ajax
    @GetMapping("/exam/subject")
    public String findSubject(){

        return "view/main/main";
    }


}
