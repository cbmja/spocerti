package com.spo.certificate.main.controller;

import com.spo.certificate.exam.dto.ExamSubject;
import com.spo.certificate.exam.dto.ExamTitle;
import com.spo.certificate.exam.service.examSubject.ExamSubjectService;
import com.spo.certificate.exam.service.examTitle.ExamTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final ExamSubjectService examSubjectService;
    private final ExamTitleService examTitleService;


    @GetMapping("/main")
    @ResponseBody
    public Map<String, Object> subject(@RequestParam(value = "examId", defaultValue = "1") int id) {
        List<ExamSubject> examSubjectList = examSubjectService.findAll();
        ExamTitle examTitle = examTitleService.findById(id);
        String electiveSubject = examTitle.getElectiveSubject();
        String requiredSubject = examTitle.getRequiredSubject();

        List<Integer> electiveSubjects = electiveSubject == null ? new ArrayList<>() : Arrays.stream(electiveSubject.split("_")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> requiredSubjects = requiredSubject == null ? new ArrayList<>() : Arrays.stream(requiredSubject.split("_")).map(Integer::parseInt).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("examTitle", examTitle.getExamTitle());
        response.put("electiveSubjects", electiveSubjects);
        response.put("requiredSubjects", requiredSubjects);
        response.put("examSubjectList", examSubjectList);

        return response;
    }

    @GetMapping("/main/main")
    public String mainPage(Model model , @RequestParam(value = "examId",defaultValue = "1")int id){

        List<ExamSubject> examSubjectList = examSubjectService.findAll();
        ExamTitle examTitle = examTitleService.findById(id);
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
        subject(1);


        return "view/main/main";
    }

}
