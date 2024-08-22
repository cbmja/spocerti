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


    public List<Integer> yearOption(int startYear){
        List<Integer> yearList = new ArrayList<>();

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int year = startYear; year < currentYear; year++) {
            yearList.add(year);
        }

        return yearList;
    }

    public List<Exam> examOption(){

        return examInfoService.findAll();
    }

    public List<String> typeOption(){
        List<String> types = new ArrayList<>();
        types.add("A");
        types.add("B");
        return types;
    }




}
