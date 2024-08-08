package com.spo.certificate.exam.mapper;

import com.spo.certificate.exam.dto.Exam;

public interface ExamMapper {

    Exam findById(int id);
    Exam findAll();

}
