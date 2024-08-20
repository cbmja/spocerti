package com.spo.certificate.board.mapper;

import com.spo.certificate.board.controller.BoardSearch;
import com.spo.certificate.board.dto.BoardData;
import com.spo.certificate.pagination.Page;

public interface BoardDataMapper {

    int save(BoardData boardData);

    int getTotal(BoardSearch search);

    BoardData findByPage(Page page);

    BoardData findById(int id);
}
