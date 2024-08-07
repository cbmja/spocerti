package com.spo.certificate.main.controller;

import com.spo.certificate.exam.dto.ExamSubject;
import com.spo.certificate.exam.dto.ExamTitle;
import com.spo.certificate.exam.service.examSubject.ExamSubjectService;
import com.spo.certificate.exam.service.examTitle.ExamTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final ExamSubjectService examSubjectService;
    private final ExamTitleService examTitleService;

    @GetMapping("/main")
    public String mainPage(Model model){

        List<ExamSubject> examSubjectList = examSubjectService.findAll();
        ExamTitle examTitle = examTitleService.findById(6);
        String electiveSubject = examTitle.getElectiveSubject();
        String requiredSubject = examTitle.getRequiredSubject();

        List<Integer> electiveSubjects = electiveSubject == null ? new ArrayList<>() : Arrays.stream(electiveSubject.split("_")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> requiredSubjects = requiredSubject == null ? new ArrayList<>() : Arrays.stream(requiredSubject.split("_")).map(Integer::parseInt).collect(Collectors.toList());

        System.out.println(electiveSubjects);
        System.out.println(requiredSubjects);

        model.addAttribute("exam" ,examTitle);
        model.addAttribute("electiveSubjects",electiveSubjects); //선택과목
        model.addAttribute("requiredSubjects",requiredSubjects); //필수과목
        model.addAttribute("examSubjectList" ,examSubjectList); //모든 과목



        return "view/main/main";
    }
}
