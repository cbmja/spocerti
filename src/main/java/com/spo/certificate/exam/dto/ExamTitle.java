package com.spo.certificate.exam.dto;

import lombok.Data;

@Data
public class ExamTitle {

    private int id;
    private String examTitle;
    private String requiredSubject;
    private String electiveSubject;

}
