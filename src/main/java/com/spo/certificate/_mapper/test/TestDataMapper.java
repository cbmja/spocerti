package com.spo.certificate._mapper.test;

import com.spo.certificate._dto.test.TestData;

import java.util.List;

public interface TestDataMapper {

    int save(TestData testData);
    List<TestData> findAll();
    int getCode();

}
