package com.spo.certificate.exam.service.examSubject;

import com.spo.certificate.exam.dto.ExamSubject;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamSubjectService {

    private final SqlSessionTemplate sql;


    public ExamSubject findById(int id){

        return sql.selectOne("com.spo.certificate.exam.mapper.ExamSubjectMapper.findById",id);
    }

    public List<ExamSubject> findAll(){

        return sql.selectList("com.spo.certificate.exam.mapper.ExamSubjectMapper.findAll");
    }

}
