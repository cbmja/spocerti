package com.spo.certificate.exam.service.examSubject;

import com.spo.certificate.exam.dto.Subject;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SqlSessionTemplate sql;


    public Subject findById(int id){

        return sql.selectOne("com.spo.certificate.exam.mapper.SubjectMapper.findById",id);
    }

    public List<Subject> findAll(){

        return sql.selectList("com.spo.certificate.exam.mapper.SubjectMapper.findAll");
    }

}
