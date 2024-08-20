package com.spo.certificate.board.service.board;

import com.spo.certificate.board.dto.Board;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardInfoService {

    private final SqlSessionTemplate sql;

    public Board findByBoardType(String boardType){

        return sql.selectOne("com.spo.certificate.board.mapper.BoardMapper.findByBoardType",boardType);
    }
}
