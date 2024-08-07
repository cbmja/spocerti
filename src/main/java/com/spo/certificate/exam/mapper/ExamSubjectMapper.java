package com.spo.certificate.exam.mapper;

import com.spo.certificate.exam.dto.ExamSubject;

import java.util.List;

public interface ExamSubjectMapper {

    ExamSubject findById(int id);
    List<ExamSubject> findAll();

}
