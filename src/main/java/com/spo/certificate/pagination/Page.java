package com.spo.certificate.pagination;


import com.spo.certificate.board.controller.BoardSearch;
import lombok.Data;


@Data
public class Page {

    private String boardType;
    private String search;
    private String searchType;

    private int total;//총 게시물 수 ok

    private int pageElement = 2;//한 페이지에 보여줄 게시물 수 ok
    private int pageSize = 5;//보여줄 페이지 블럭 수 ok

    private int startPage;//보여지는 블럭의 시작번호
    private int endPage;//보여지는 블럭의 마지막 번호


    private int page=1; //현재 페이지 ok
    private int totalPage;//총 페이지 수

    private int startNum;//첫 게시물 번호 ok
    private int endNum;//마지막 게시물 번호 ok

    //private int currentPageBlock;//현재 보이는 페이지 블럭 수

    private boolean next;//다음페이지 존재 여부
    private boolean prev;//이전페이지 존재 여부

    private int prev_;
    private int next_;

    //게시판 검색
    public Page(int page , int total , BoardSearch search){

        this.boardType = search.getBoardType();
        this.search = search.getSearch();
        this.searchType = search.getSearchType();

        this.total = total;
        this.totalPage = total % this.pageElement > 0 ? total / this.pageElement + 1 : total / this.pageElement;

        this.page = page < 1 ? 1 : page;
        if(this.page > this.totalPage){
            this.page = this.totalPage;
        }


        this.startNum = (this.page - 1)*this.pageElement < 0 ? 0 : (this.page - 1)*this.pageElement;
        this.endNum = this.page == this.totalPage ? this.total : startNum + this.pageElement - 1;


        this.endPage = (( this.startNum / (this.pageElement * this.pageSize) ) * this.pageSize) + this.pageSize > this.totalPage
                ? this.totalPage : (( this.startNum / (this.pageElement * this.pageSize) ) * this.pageSize) + this.pageSize;

        this.startPage = ((( this.startNum / (this.pageElement * this.pageSize) ) * this.pageSize) + this.pageSize)-this.pageSize+1;

        if(this.totalPage % this.pageSize == 0){

        }else{
            if(this.totalPage > this.pageSize){

            }
            if(this.totalPage < this.pageSize){

            }

        }

        if(this.startPage == 1){
            this.prev = false;
        }else{
            this.prev = true;
        }

        if(this.endPage == this.totalPage){
            this.next = false;
        }else{
            this.next = true;
        }

        if(this.prev){ this.prev_ = this.startPage -1;
        }else{this.prev_ =1;}

        if(this.next){this.next_ = this.endPage+1;
        }else{this.next_ = this.totalPage;}

    }


}

