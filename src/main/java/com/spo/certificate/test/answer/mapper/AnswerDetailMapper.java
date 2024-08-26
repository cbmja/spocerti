package com.spo.certificate.test.answer.mapper;

import com.spo.certificate.test.answer.dto.AnswerDetail;

import java.util.List;

public interface AnswerDetailMapper {

    List<AnswerDetail> findByAnswerCode(int answerCode);


}
