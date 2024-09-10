package com.spo.certificate._service.answer;

import com.spo.certificate._dto.answer.Answer;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final SqlSessionTemplate sql;


    public Answer findByExamCodeAndSubjectCodeAndYearAndType(Answer answer){

        return sql.selectOne("com.spo.certificate._mapper.answer.AnswerMapper.findByExamCodeAndSubjectCodeAndYearAndType",answer);
    }

    public List<Answer> findAll(){

        return sql.selectOne("com.spo.certificate._mapper.answer.AnswerMapper.findAll");
    }
    public int getCode(){

        return sql.selectOne("com.spo.certificate._mapper.AnswerMapper.getCode");
    }
}
