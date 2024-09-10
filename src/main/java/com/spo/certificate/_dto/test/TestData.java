package com.spo.certificate._dto.test;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestData {

    private int testDataCode;
    private int userCode;
    private int examCode;
    private int examYear;
    private String examType;
    private LocalDateTime testDate;
    private int totalScore;
    private String testResult;

}
