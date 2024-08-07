package com.spo.certificate.exam.mapper;


import com.spo.certificate.exam.dto.Subject;

import java.util.List;

public interface SubjectMapper {

    Subject findById(int id);
    List<Subject> findAll();

}
