package com.spo.certificate.test.answer.mapper;

import com.spo.certificate.test.answer.dto.Answer;

public interface AnswerMapper {

    Answer findByExamCodeAndSubjectCodeAndYearAndType(Answer form);

}
