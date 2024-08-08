package com.spo.certificate.main.selectOption;

import com.spo.certificate.exam.dto.Exam;
import com.spo.certificate.exam.service.exam.ExamInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SelectOption {

    private final ExamInfoService examInfoService;


    public List<Exam> examOptions(){

        return examInfoService.findAll();
    }

    public List<String> typeOptions(){
        List<String> typeOptions = new ArrayList<>();
        typeOptions.add("A");
        typeOptions.add("B");
        return typeOptions;
    }

    public List<Integer> yearOptions(){

        Calendar calendar = Calendar.getInstance();
        //현재 년도
        int currentYear = calendar.get(Calendar.YEAR);
        //현재 월
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH는 0부터 시작하므로 +1


        int endYear = (currentMonth > 7) ? currentYear : currentYear - 1;

        // 2015년부터 endYear까지의 리스트 생성
        ArrayList<Integer> yearOptions = new ArrayList<>();
        for (int year = 2015; year <= endYear; year++) {
            yearOptions.add(year);
        }

        return yearOptions;
    }

}
