package com.spo.certificate.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardData {


    private int id;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int viewCnt;
    private String boardType;
    private int likes;
}
