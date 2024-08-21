package com.spo.certificate.exam.controller;

import com.spo.certificate.exam.dto.Exam;
import com.spo.certificate.exam.dto.ExamData;
import com.spo.certificate.exam.dto.Subject;
import com.spo.certificate.exam.service.exam.ExamInfoService;
import com.spo.certificate.exam.service.examData.ExamDataInfoService;
import com.spo.certificate.exam.service.subject.SubjectInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ExamController {

    private final ExamDataInfoService examDataInfoService;
    private final SubjectInfoService subjectInfoService;
    private final ExamInfoService examInfoService;

    @PostMapping("/exam/grading")
    public ResponseEntity<String> grading(@RequestBody Map<String, String> formData) {
        System.out.println(formData);

        return ResponseEntity.ok("Grading data received and processed successfully.");
    }

    @GetMapping("/exam")
    public String test(Model model , @RequestParam(name = "examDataId") int examDataId){


        ExamData examData = examDataInfoService.findById(examDataId);
        Exam exam = examInfoService.findById(examData.getExamId());
        String electiveSubject = exam.getElectiveSubject();
        String requiredSubject = exam.getRequiredSubject();

        List<Integer> electiveSubjectId = electiveSubject == null ? new ArrayList<>() : Arrays.stream(electiveSubject.split("_")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> requiredSubjectId = requiredSubject == null ? new ArrayList<>() : Arrays.stream(requiredSubject.split("_")).map(Integer::parseInt).collect(Collectors.toList());

        List<Subject> electiveSubjects = new ArrayList<>();//선택
        List<Subject> requiredSubjects = new ArrayList<>();//필수

        for(int id : electiveSubjectId){
            electiveSubjects.add(subjectInfoService.findById(id));
        }
        for(int id : requiredSubjectId){
            requiredSubjects.add(subjectInfoService.findById(id));
        }

        model.addAttribute("electiveSubjects",electiveSubjects);
        model.addAttribute("requiredSubjects",requiredSubjects);
        model.addAttribute("electiveCnt",exam.getElectiveCnt());
        model.addAttribute("examData" , examData);
        return "view/exam/exam";
    }

}
