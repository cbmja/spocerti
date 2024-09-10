package com.spo.certificate._service.subject;

import com.spo.certificate._dto.subject.Subject;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SqlSessionTemplate sql;

    public Subject findBySubjectCode(int subjectCode){
        return sql.selectOne("com.spo.certificate._mapper.subject.SubjectMapper.findBySubjectCode",subjectCode);
    }

    public List<Subject> findAll(){
        return sql.selectList("com.spo.certificate._mapper.findBySubjectCode.findAll");
    }
    public int getCode(){
        return sql.selectOne("com.spo.certificate._mapper.subject.findBySubjectCode.getCode");
    }
}
