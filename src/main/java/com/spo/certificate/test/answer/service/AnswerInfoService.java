package com.spo.certificate.test.answer.service;

import com.spo.certificate.test.answer.dto.Answer;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerInfoService {

    private final SqlSessionTemplate sql;

    public Answer findByExamCodeAndSubjectCodeAndYearAndType(Answer form){
        return sql.selectOne("com.spo.certificate.test.answer.mapper.AnswerMapper.findByExamCodeAndSubjectCodeAndYearAndType",form);
    }

}
