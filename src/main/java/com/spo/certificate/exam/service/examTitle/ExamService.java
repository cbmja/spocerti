package com.spo.certificate.exam.service.examTitle;

import com.spo.certificate.exam.dto.Exam;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final SqlSessionTemplate sql;
    public Exam findById(int id){

        return sql.selectOne("com.spo.certificate.exam.mapper.ExamMapper.findById",id);
    }
}
