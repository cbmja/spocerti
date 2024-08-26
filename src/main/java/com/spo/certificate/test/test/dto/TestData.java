package com.spo.certificate.test.test.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestData {

    private int code;
    private int userCode;
    private int examCode;
    private int year;
    private String type;
    private LocalDateTime testDate;
    private int score;
    private String result;


}
