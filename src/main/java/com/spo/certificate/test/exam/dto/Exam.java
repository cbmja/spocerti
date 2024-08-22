package com.spo.certificate.test.exam.dto;

import lombok.Data;

@Data
public class Exam {

    private int code;
    private String title;
    private int requiredSubjectCnt;
    private int electiveSubjectCnt;
    private int vitalElectiveSubjectCnt;
    private int startYear;



}
