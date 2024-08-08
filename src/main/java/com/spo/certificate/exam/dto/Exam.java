package com.spo.certificate.exam.dto;

import lombok.Data;

@Data
public class Exam {

    private int id;
    private String examTitle;
    private String requiredSubject;
    private String electiveSubject;
    private int electiveCnt;

}
