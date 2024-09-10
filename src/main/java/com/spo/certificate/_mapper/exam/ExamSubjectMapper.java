package com.spo.certificate._mapper.exam;

import com.spo.certificate._dto.exam.ExamSubject;

import java.util.List;

public interface ExamSubjectMapper {

    List<ExamSubject> findByExamCode(int examCode);
    ExamSubject findByExamCodeAndSubjectCode(ExamSubject examSubject);
    List<ExamSubject> findAll();
    int getCode();



}
