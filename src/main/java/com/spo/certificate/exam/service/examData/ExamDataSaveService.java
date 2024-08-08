package com.spo.certificate.exam.service.examData;

import com.spo.certificate.exam.dto.ExamData;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamDataSaveService {
    private final SqlSessionTemplate sql;


    public int save(ExamData examData){
        return sql.insert("com.spo.certificate.exam.mapper.ExamDataMapper.save",examData);
    }

}
