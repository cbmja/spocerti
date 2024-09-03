package com.spo.certificate.test.examSubject.service;

import com.spo.certificate.test.examSubject.dto.ExamSubject;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamSubjectInfoService {

    private final SqlSessionTemplate sql;

    public List<ExamSubject> findByExamCode(int examCode){

        return sql.selectList("com.spo.certificate.test.examSubject.mapper.ExamSubjectMapper.findByExamCode", examCode);
    }

    public ExamSubject findByExamCodeAndSubjectCode(ExamSubject examSubject){
        return sql.selectOne("com.spo.certificate.test.examSubject.mapper.ExamSubjectMapper.findByExamCodeAndSubjectCode", examSubject);
    }

}
