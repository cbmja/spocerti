package com.spo.certificate._service.exam;

import com.spo.certificate._dto.exam.ExamSubject;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamSubjectService {
    private final SqlSessionTemplate sql;

    public List<ExamSubject> findByExamCode(int examCode){
        return sql.selectList("com.spo.certificate._mapper.exam.ExamSubjectMapper.findByExamCode",examCode);
    }

    public ExamSubject findByExamCodeAndSubjectCode(ExamSubject examSubject){
        return sql.selectOne("com.spo.certificate._mapper.exam.ExamSubjectMapper.findByExamCodeAndSubjectCode",examSubject);
    }

    public List<ExamSubject> findAll(){
        return sql.selectList("com.spo.certificate._mapper.exam.ExamSubjectMapper.findAll");
    }

    public int getCode(){
        return sql.selectOne("com.spo.certificate._mapper.ExamSubjectMapper.getCode");
    }
}
