package com.spo.certificate._mapper.answer;


import com.spo.certificate._dto.answer.Answer;

import java.util.List;

public interface AnswerMapper {

    Answer findByExamCodeAndSubjectCodeAndYearAndType(Answer answer);
    List<Answer> findAll();
    int getCode();

}
