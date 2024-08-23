package com.spo.certificate.test.subject.service;

import com.spo.certificate.test.subject.dto.Subject;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectInfoService {

    private final SqlSessionTemplate sql;

    public Subject findByCode(int code){

        return sql.selectOne("com.spo.certificate.test.subject.mapper.SubjectMapper.findByCode" , code);
    }

}
