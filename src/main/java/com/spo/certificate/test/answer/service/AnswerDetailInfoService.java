package com.spo.certificate.test.answer.service;

import com.spo.certificate.test.answer.dto.AnswerDetail;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerDetailInfoService {

    private final SqlSessionTemplate sql;

    public List<AnswerDetail> findByAnswerCode(int answerCode){

        return sql.selectList("com.spo.certificate.test.answer.mapper.AnswerDetailMapper.findByAnswerCode",answerCode);
    }

}
