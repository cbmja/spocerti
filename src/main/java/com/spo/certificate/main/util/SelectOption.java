package com.spo.certificate.main.util;

import com.spo.certificate.test.exam.dto.Exam;
import com.spo.certificate.test.exam.service.ExamInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SelectOption {

    private final ExamInfoService examInfoService;


    public List<String> yearOption(int code){
        List<String> yearList = new ArrayList<>();
        yearList.add("ALL");

        Exam exam = examInfoService.findByCode(code);
        for(int i = exam.getStartYear(); i<= exam.getUpdateYear(); i++){
            yearList.add(i+"");
        }

        return yearList;
    }

    public List<Exam> examOption(){

        return examInfoService.findAll();
    }

    public List<String> typeOption(){
        List<String> types = new ArrayList<>();
        types.add("All");
        types.add("A");
        types.add("B");
        return types;
    }




}
