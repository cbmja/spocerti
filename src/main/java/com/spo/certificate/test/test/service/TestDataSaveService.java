package com.spo.certificate.test.test.service;

import com.spo.certificate.test.test.dto.TestData;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestDataSaveService {

    private final SqlSessionTemplate sql;

    public int save(TestData testData){

        return sql.insert("com.spo.certificate.test.test.mapper.TestDataMapper.save",testData);

    }

    public int getCode(){

        return sql.selectOne("com.spo.certificate.test.test.mapper.TestDataMapper.getCode");

    }


}
