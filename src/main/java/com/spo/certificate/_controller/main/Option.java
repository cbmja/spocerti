package com.spo.certificate._controller.main;

import com.spo.certificate._dto.exam.Exam;
import com.spo.certificate._service.exam.ExamService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class Option {

    private final ExamService examService;

    public List<String> yearOption(int code){
        List<String> yearList = new ArrayList<>();
        yearList.add("ALL");

        Exam exam = examService.findByExamCode(code);
        for(int i = exam.getStartYear(); i<= exam.getUpdateYear(); i++){
            yearList.add(i+"");
        }

        return yearList;
    }

    public List<Exam> examOption(){

        return examService.findAll();
    }



}
