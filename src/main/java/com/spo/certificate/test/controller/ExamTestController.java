package com.spo.certificate.test.controller;

import com.spo.certificate.test.answer.dto.Answer;
import com.spo.certificate.test.answer.service.AnswerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ExamTestController {

    private final AnswerInfoService answerInfoService;

    @PostMapping("/test/submit")
    public void gradingTest(@RequestParam Map<String,String> form){
        Answer data = new Answer();
        data.setType(form.get("examType"));
        data.setYear(Integer.parseInt(form.get("examYear")));
        data.setExamCode(Integer.parseInt(form.get("examCode")));

        List<Integer> subjectCodes = new ArrayList<>();

        for(Map.Entry<String,String> entry : form.entrySet()){
            if(entry.getKey().length() == 1){
                subjectCodes.add(Integer.parseInt(entry.getKey()));
            }
        }
        System.out.println(subjectCodes);

        List<Answer> answers = new ArrayList<>();
        for(int i : subjectCodes){
            data.setSubjectCode(i);
            answers.add(answerInfoService.findByExamCodeAndSubjectCodeAndYearAndType(data));
        }





    }


}
