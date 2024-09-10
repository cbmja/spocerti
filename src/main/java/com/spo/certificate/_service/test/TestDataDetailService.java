package com.spo.certificate._service.test;

import com.spo.certificate._dto.test.TestDataDetail;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestDataDetailService {
    private final SqlSessionTemplate sql;


    public int save(TestDataDetail testDataDetail){
        return sql.insert("com.spo.certificate._mapper.test.TestDataDetailMapper.save",testDataDetail);
    }
    public List<TestDataDetail> findAll(){
        return sql.selectList("com.spo.certificate._mapper.test.TestDataDetailMapper.findAll");
    }
    public int getCode(){
        return sql.selectOne("com.spo.certificate._mapper.test.TestDataDetailMapper.getCode");
    }

}
