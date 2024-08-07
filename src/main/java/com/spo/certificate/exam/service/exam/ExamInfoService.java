package com.spo.certificate.exam.service.exam;

import com.spo.certificate.exam.dto.Exam;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamInfoService {
    private final SqlSessionTemplate sql;
    public Exam findById(int id){

        return sql.selectOne("com.spo.certificate.exam.mapper.ExamMapper.findById",id);
    }

    public List<Exam> findAll(){

        return sql.selectList("com.spo.certificate.exam.mapper.ExamMapper.findAll");
    }
}
