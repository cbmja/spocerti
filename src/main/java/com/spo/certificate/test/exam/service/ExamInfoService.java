package com.spo.certificate.test.exam.service;

import com.spo.certificate.test.exam.dto.Exam;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamInfoService {

    private final SqlSessionTemplate sql;

    public List<Exam> findAll(){
        return sql.selectList("com.spo.certificate.test.exam.mapper.ExamMapper.findAll");
    }

}
