package com.spo.certificate._dto.exam;

import lombok.Data;

@Data
public class Exam {

    private int examCode;
    private String examTitle;
    private int requiredSubjectCnt;
    private int electiveSubjectCnt;
    private int vitalElectiveSubjectCnt;
    private int startYear;
    private int updateYear;
    private int examPassingScore;


}
