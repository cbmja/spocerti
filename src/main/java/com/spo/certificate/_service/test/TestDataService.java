package com.spo.certificate._service.test;

import com.spo.certificate._dto.test.TestData;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestDataService {
    private final SqlSessionTemplate sql;

    public int save(TestData testData){
        return sql.insert("com.spo.certificate._mapper.test.TestDataMapper.save",testData);
    }
    public List<TestData> findAll(){
        return sql.selectList("com.spo.certificate._mapper.test.TestDataMapper.findAll");
    }
    public int getCode(){
        return sql.selectOne("com.spo.certificate._mapper.test.TestDataMapper.getCode");
    }
}
