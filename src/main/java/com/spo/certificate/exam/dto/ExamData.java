package com.spo.certificate.exam.dto;

import lombok.Data;

@Data
public class ExamData {

    private int id;
    private int year;
    private char type;
    private int examId;
    private String path;
    private String examTitle;


}
