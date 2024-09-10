package com.spo.certificate._controller.main;

import com.spo.certificate._dto.exam.Exam;
import com.spo.certificate._dto.exam.ExamSubject;
import com.spo.certificate._dto.subject.Subject;
import com.spo.certificate._service.exam.ExamService;
import com.spo.certificate._service.exam.ExamSubjectService;
import com.spo.certificate._service.subject.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final Option option;
    private final ExamService examService;
    private final ExamSubjectService examSubjectService;
    private final SubjectService subjectService;



    @GetMapping("/main")
    public String main(Model model){


        model.addAttribute("examOpts" , option.examOption());

        return "view/main/main";
    }


    /* ajax */
    @GetMapping("/main/exam/select")
    @ResponseBody
    public List<String> selectExam(@RequestParam("code") int code){
        return option.yearOption(code);
    }

    @GetMapping("/main/exam/search")
    @ResponseBody
    public List<ExamSearch> getList(@RequestParam Map<String,String> form){

        List<ExamSearch> response = new ArrayList<>();
        Exam exam = examService.findByExamCode(Integer.parseInt(form.get("examCode")));
        if(form.get("examYear").equals("ALL")){

            for(int i = exam.getStartYear(); i<=exam.getUpdateYear(); i++){
                ExamSearch examSearch = new ExamSearch();
                examSearch.setExamTitle(exam.getExamTitle());
                examSearch.setExamYear(i);
                response.add(examSearch);
            }
        }else{
            ExamSearch examSearch = new ExamSearch();
            examSearch.setExamTitle(exam.getExamTitle());
            examSearch.setExamYear(Integer.parseInt(form.get("examYear")));
            response.add(examSearch);
        }

        return response;
    }

    @GetMapping("/main/exam/take")
    public String takeExam(@RequestParam Map<String,String> form , Model model){

        int examCode = Integer.parseInt(form.get("examCode"));

        List<ExamSubject> examSubjects = examSubjectService.findByExamCode(examCode);

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
            Subject subject = subjectService.findBySubjectCode(code);
            if(subject != null){
                requiredSubjects.add(subject);
            }
        }

        for(int code : electiveSubjectsCodes){
            Subject subject = subjectService.findBySubjectCode(code);
            if(subject != null){
                electiveSubjects.add(subject);
            }

        }

        model.addAttribute("exam" , examService.findByExamCode(examCode));
        model.addAttribute("year" , form.get("examYear"));
        model.addAttribute("type" , form.get("examType"));
        model.addAttribute("requiredSubjects" , requiredSubjects);
        model.addAttribute("electiveSubjects" , electiveSubjects);
        System.out.println(form);

        return "view/test/take-exam";
    }


}
