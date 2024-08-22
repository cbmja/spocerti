package com.spo.certificate.test.exam.mapper;

import com.spo.certificate.test.exam.dto.Exam;

import java.util.List;

public interface ExamMapper {

    List<Exam> findAll();

    Exam findByCode(int code);

}
