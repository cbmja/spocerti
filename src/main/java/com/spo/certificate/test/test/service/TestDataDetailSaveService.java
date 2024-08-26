package com.spo.certificate.test.test.service;

import com.spo.certificate.test.test.dto.TestDataDetail;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestDataDetailSaveService {

    private final SqlSessionTemplate sql;

    public int save(TestDataDetail testDataDetail){
        return sql.insert("com.spo.certificate.test.test.mapper.TestDataDetailMapper.save",testDataDetail);
    }
}
