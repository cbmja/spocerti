package com.spo.certificate._mapper.subject;

import com.spo.certificate._dto.subject.Subject;

import java.util.List;

public interface SubjectMapper {

    Subject findBySubjectCode(int subjectCode);
    List<Subject> findAll();
    int getCode();


}
