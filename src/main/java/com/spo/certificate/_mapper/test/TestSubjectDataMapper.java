package com.spo.certificate._mapper.test;

import com.spo.certificate._dto.test.TestSubjectData;

import java.util.List;

public interface TestSubjectDataMapper {

    int save(TestSubjectData testSubjectData);
    List<TestSubjectData> findAll();
    int getCode();

}
