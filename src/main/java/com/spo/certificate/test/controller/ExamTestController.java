package com.spo.certificate.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ExamTestController {


    @PostMapping("/test/submit")
    public String gradingTest(@RequestParam Map<String,String> form){
        System.out.println(form);
        return null;
    }


}
