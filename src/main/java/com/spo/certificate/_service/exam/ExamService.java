package com.spo.certificate._service.exam;

import com.spo.certificate._dto.exam.Exam;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final SqlSessionTemplate sql;

    public Exam findByExamCode(int examCode){
        return sql.selectOne("com.spo.certificate._mapper.exam.ExamMapper.findByExamCode",examCode);
    }
    public List<Exam> findAll(){
        return sql.selectList("com.spo.certificate._mapper.exam.ExamMapper.findAll");
    }
}
