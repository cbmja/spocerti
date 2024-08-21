package com.spo.certificate.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamController {


    @PostMapping
    public String grading(){


        return "";
    }

    @GetMapping("/exam")
    public String test(@RequestParam(name = "examId") int examId){

        return "view/exam/exam";
    }

}
