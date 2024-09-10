package com.spo.certificate._mapper.test;

import com.spo.certificate._dto.test.TestDataDetail;

import java.util.List;

public interface TestDataDetailMapper {


    int save(TestDataDetail testDataDetail);
    List<TestDataDetail> findAll();
    int getCode();

}
