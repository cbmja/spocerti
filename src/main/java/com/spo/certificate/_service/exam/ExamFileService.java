package com.spo.certificate._service.exam;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamFileService {
    private final SqlSessionTemplate sql;
}
