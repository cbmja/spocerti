package com.spo.certificate.main.controller;

import com.spo.certificate.exam.dto.Exam;
import com.spo.certificate.exam.dto.Subject;
import com.spo.certificate.exam.service.exam.ExamInfoService;
import com.spo.certificate.exam.service.examData.ExamDataInfoService;
import com.spo.certificate.exam.service.subject.SubjectInfoService;
import com.spo.certificate.main.selectOption.SelectOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final SubjectInfoService subjectInfoService;
    private final ExamInfoService examInfoService;
    private final ExamDataInfoService examDataInfoService;
    private final SelectOption selectOption;

    @GetMapping("/main/subject")
    @ResponseBody
    public Map<String, Object> subject(@RequestParam(value = "examId", defaultValue = "1") int id) {
        List<Subject> examSubjectList = subjectInfoService.findAll();
        Exam exam = examInfoService.findById(id);
        String electiveSubject = exam.getElectiveSubject();
        String requiredSubject = exam.getRequiredSubject();

        List<Integer> electiveSubjects = electiveSubject == null ? new ArrayList<>() : Arrays.stream(electiveSubject.split("_")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> requiredSubjects = requiredSubject == null ? new ArrayList<>() : Arrays.stream(requiredSubject.split("_")).map(Integer::parseInt).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("examTitle", exam.getExamTitle());
        response.put("electiveSubjects", electiveSubjects);
        response.put("requiredSubjects", requiredSubjects);
        response.put("examSubjectList", examSubjectList);
        response.put("electiveCnt", exam.getElectiveCnt());
        return response;
    }

    @GetMapping("/main")
    public String mainPage(Model model , @ModelAttribute MainSearch form){


        if(form.getType().equals("ALL")){
            model.addAttribute("examDataList",examDataInfoService.findByExamId(form));
        }else {
            model.addAttribute("examDataList",examDataInfoService.findByYearAndExamId(form));
        }
        model.addAttribute("examOptions",selectOption.examOptions());
        model.addAttribute("typeOptions",selectOption.typeOptions());
        model.addAttribute("yearOptions",selectOption.yearOptions());
        model.addAttribute("search",form);

        return "view/main/main";
    }

}
