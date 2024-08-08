package com.spo.certificate.exam.mapper;

import com.spo.certificate.main.controller.MainSearch;
import com.spo.certificate.exam.dto.ExamData;

import java.util.List;


public interface ExamDataMapper {

    int save(ExamData examData);

    List<ExamData> findByYearAndExamId(MainSearch form);

}
