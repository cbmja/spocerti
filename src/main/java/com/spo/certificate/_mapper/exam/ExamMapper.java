package com.spo.certificate._mapper.exam;

import com.spo.certificate._dto.exam.Exam;
import com.spo.certificate._dto.exam.ExamSubject;

import java.util.List;

public interface ExamMapper {

    Exam findByExamCode(int examCode);
    List<Exam> findAll();
    int getCode();

}
