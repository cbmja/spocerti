package com.spo.certificate._dto.answer;

import lombok.Data;

@Data
public class AnswerDetail {

    private int answerDetailCode;
    private int answerCode;
    private int questionNo;
    private int answer;
    private int points;
}
