package com.spo.certificate._controller.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ExamTestController {

    @PostMapping("/test/submit")
    public String submitTest(){
        return "";
    }

}
