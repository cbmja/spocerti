package com.spo.certificate.board.mapper;

import com.spo.certificate.board.dto.Board;

public interface BoardMapper {

    Board findByBoardType(String boardType);

}
