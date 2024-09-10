package com.spo.certificate._mapper.answer;

import com.spo.certificate._dto.answer.AnswerDetail;

import java.util.List;

public interface AnswerDetailMapper {


    List<AnswerDetail> findByAnswerCode(int answerCode);
    List<AnswerDetail> findAll();
    int getCode();


}
