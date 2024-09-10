package com.spo.certificate._dto.exam;

import lombok.Data;

@Data
public class ExamFile {

    private int examFileCode;
    private int examYear;
    private String examType;
    private int examCode;
    private String filePath;
    private String fileName;

}
