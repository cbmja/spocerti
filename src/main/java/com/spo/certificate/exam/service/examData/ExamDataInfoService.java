package com.spo.certificate.exam.service.examData;

import com.spo.certificate.main.controller.MainSearch;
import com.spo.certificate.exam.dto.ExamData;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamDataInfoService {

    private final SqlSessionTemplate sql;

    public ExamData findById(int examDataId){

        return sql.selectOne("com.spo.certificate.exam.mapper.ExamDataMapper.findById",examDataId);
    }

    public List<ExamData> findByYearAndExamId(MainSearch form){

        return sql.selectList("com.spo.certificate.exam.mapper.ExamDataMapper.findByYearAndExamId",form);
    }
    public List<ExamData> findByExamId(int examId){

        return sql.selectList("com.spo.certificate.exam.mapper.ExamDataMapper.findByExamId",examId);
    }
}
