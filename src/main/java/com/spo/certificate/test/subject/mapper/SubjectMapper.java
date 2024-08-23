package com.spo.certificate.test.subject.mapper;

import com.spo.certificate.test.subject.dto.Subject;

public interface SubjectMapper {

    Subject findByCode(int code);

}
