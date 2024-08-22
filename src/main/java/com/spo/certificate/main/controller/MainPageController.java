package com.spo.certificate.main.controller;

import com.spo.certificate.main.util.SelectOption;
import com.spo.certificate.test.exam.service.ExamInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final ExamInfoService examInfoService;
    private final SelectOption selectOption;

    @GetMapping("/main")
    public String main(Model model){



        model.addAttribute("typeOpts" , selectOption.typeOption());
        model.addAttribute("examOpts" , selectOption.examOption());

        return "view/main/main";
    }

}
