package com.spo.certificate.main.controller;

import com.spo.certificate.main.util.SelectOption;
import com.spo.certificate.test.exam.dto.Exam;
import com.spo.certificate.test.exam.service.ExamInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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






    /* ajax */
    @GetMapping("/main/select/exam")
    @ResponseBody
    public List<String> selectExam(@RequestParam("code") int code){
        return selectOption.yearOption(code);
    }

}
