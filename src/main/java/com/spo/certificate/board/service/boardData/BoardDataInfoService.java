package com.spo.certificate.board.service.boardData;

import com.spo.certificate.board.controller.BoardSearch;
import com.spo.certificate.board.dto.BoardData;
import com.spo.certificate.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardDataInfoService {

    private final SqlSessionTemplate sql;

    public int getTotal(BoardSearch search){
        return sql.selectOne("com.spo.certificate.board.mapper.BoardDataMapper.getTotal",search);
    }

    public BoardData findById(int id){
        return sql.selectOne("com.spo.certificate.board.mapper.BoardDataMapper.findById",id);
    }

    public List<BoardData> findByPage(Page page){
        return sql.selectList("com.spo.certificate.board.mapper.BoardDataMapper.findByPage",page);
    }

}
