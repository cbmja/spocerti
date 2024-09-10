package com.spo.certificate._dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private int userCode;
    private String id;
    private String password;
    private String email;
    private String major;
    private String job;
    private int age;
    private LocalDateTime joinDate;
    private String status;



}
