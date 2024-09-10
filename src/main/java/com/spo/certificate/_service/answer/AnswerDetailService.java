package com.spo.certificate._service.answer;

import com.spo.certificate._dto.answer.AnswerDetail;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerDetailService {

    private final SqlSessionTemplate sql;

    public List<AnswerDetail> findByAnswerCode(int answerCode){
        return sql.selectList("com.spo.certificate._mapper.answer.AnswerDetailMapper.findByAnswerCode",answerCode);
    }
    public List<AnswerDetail> findAll(int answerCode){
        return sql.selectList("com.spo.certificate._mapper.answer.AnswerDetailMapper.findAll",answerCode);
    }
    public int getCode(){
        return sql.selectOne("com.spo.certificate._mapper.answer.AnswerDetailMapper.getCode");
    }



}
