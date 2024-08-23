package com.spo.certificate.test.examSubject.mapper;

import com.spo.certificate.test.examSubject.dto.ExamSubject;

import java.util.List;

public interface ExamSubjectMapper {


    List<ExamSubject> findByExamCode(int examCode);

}
