package com.spo.certificate.main.selectOption;

import com.spo.certificate.exam.dto.Exam;
import com.spo.certificate.exam.service.exam.ExamInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SelectOption {

    private ExamInfoService examInfoService;

/*    public List<Exam> examOptions(){

        return examInfoService
    }*/

}
