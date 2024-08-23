package com.spo.certificate.main.controller;

import com.spo.certificate.main.util.SelectOption;
import com.spo.certificate.test.exam.dto.Exam;
import com.spo.certificate.test.exam.service.ExamInfoService;
import com.spo.certificate.test.examSubject.dto.ExamSubject;
import com.spo.certificate.test.examSubject.service.ExamSubjectInfoService;
import com.spo.certificate.test.subject.dto.Subject;
import com.spo.certificate.test.subject.service.SubjectInfoService;
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

    private final ExamSubjectInfoService examSubjectInfoService;

    private final SubjectInfoService subjectInfoService;

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

    @GetMapping("/main/exam/take")
    public String takeExam(@RequestParam Map<String,String> form , Model model){

        int examCode = Integer.parseInt(form.get("examCode"));

        List<ExamSubject> examSubjects = examSubjectInfoService.findByExamCode(examCode);

        //필수 과목 코드
        List<Integer> requiredSubjectsCodes = new ArrayList<>();

        //선택 과목 코드
        List<Integer> electiveSubjectsCodes = new ArrayList<>();

        for(ExamSubject examSubject : examSubjects){
            if(examSubject.getSubjectType().equals("elective")){
                electiveSubjectsCodes.add(examSubject.getSubjectCode());
            }else {
                requiredSubjectsCodes.add((examSubject.getSubjectCode()));
            }
        }

        //필수 과목
        List<Subject> requiredSubjects = new ArrayList<>();

        //선택 과목
        List<Subject> electiveSubjects = new ArrayList<>();

        for(int code : requiredSubjectsCodes){
            Subject subject = subjectInfoService.findByCode(code);
            if(subject != null){
                requiredSubjects.add(subject);
            }
        }

        for(int code : electiveSubjectsCodes){
            Subject subject = subjectInfoService.findByCode(code);
            if(subject != null){
                electiveSubjects.add(subject);
            }

        }

        model.addAttribute("exam" , examInfoService.findByCode(examCode));
        model.addAttribute("year" , form.get("examYear"));
        model.addAttribute("type" , form.get("examType"));
        model.addAttribute("requiredSubjects" , requiredSubjects);
        model.addAttribute("electiveSubjects" , electiveSubjects);
        System.out.println(form);

        return "view/test/take-exam";
    }


}
