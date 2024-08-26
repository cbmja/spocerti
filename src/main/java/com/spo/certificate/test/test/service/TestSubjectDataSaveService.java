package com.spo.certificate.test.test.service;

import com.spo.certificate.test.test.dto.TestSubjectData;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestSubjectDataSaveService {

    private final SqlSessionTemplate sql;


    public int save(TestSubjectData testSubjectData){
        return sql.insert("com.spo.certificate.test.test.mapper.TestSubjectDataMapper.save",testSubjectData);
    }

    public int getCode(){
        return sql.selectOne("com.spo.certificate.test.test.mapper.TestSubjectDataMapper.getCode");

    }

}
