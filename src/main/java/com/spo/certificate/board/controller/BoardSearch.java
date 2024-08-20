package com.spo.certificate.board.controller;

import lombok.Data;

@Data
public class BoardSearch {

    private String boardType = "free"; //게시판 타입
    private String search = ""; //검색어
    private String searchType = "title";
    private int page = 1;

}
