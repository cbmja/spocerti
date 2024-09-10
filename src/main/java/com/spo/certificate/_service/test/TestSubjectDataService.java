package com.spo.certificate._service.test;

import com.spo.certificate._dto.test.TestSubjectData;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestSubjectDataService {
    private final SqlSessionTemplate sql;


    public int save(TestSubjectData testSubjectData){
        return sql.insert("com.spo.certificate._mapper.test.TestSubjectDataMapper.save",testSubjectData);
    }

    public List<TestSubjectData> findAll(){
        return sql.selectList("com.spo.certificate._mapper.test.TestSubjectDataMapper.findAll");
    }
    public int getCode(){
        return sql.selectOne("com.spo.certificate._mapper.test.TestSubjectDataMapper.getCode");
    }
}
