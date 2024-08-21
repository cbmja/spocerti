package com.spo.certificate.exam.controller;

import lombok.Data;
import java.util.Map;

@Data
public class gradingForm {


    private String userId; //응시자 id
    private Map<Integer , String> formList; //정답 form
    private int examId; //시험 번호
    //소요시간

}
