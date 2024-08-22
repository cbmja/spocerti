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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final ExamInfoService examInfoService;
    private final SelectOption selectOption;

    @GetMapping("/main")
    public String main(Model model){


        model.addAttribute("examOpts" , selectOption.examOption());

        return "view/main/main";
    }






    /* ajax */
    @GetMapping("/main/exam/select")
    @ResponseBody
    public List<String> selectExam(@RequestParam("code") int code){
        return selectOption.yearOption(code);
    }

    @GetMapping("/main/exam/search")
    @ResponseBody
    public List<ExamSearch> getList(@RequestParam Map<String,String> form){

        List<ExamSearch> response = new ArrayList<>();
        Exam exam = examInfoService.findByCode(Integer.parseInt(form.get("examCode")));
        if(form.get("year").equals("ALL")){

            for(int i = exam.getStartYear(); i<=exam.getUpdateYear(); i++){
                ExamSearch examSearch = new ExamSearch();
                examSearch.setExamTitle(exam.getTitle());
                examSearch.setYear(i);
                response.add(examSearch);
            }
        }else{
            ExamSearch examSearch = new ExamSearch();
            examSearch.setExamTitle(exam.getTitle());
            examSearch.setYear(Integer.parseInt(form.get("year")));
            response.add(examSearch);
        }

        return response;
    }


}
