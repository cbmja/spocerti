package com.spo.certificate.board.service.boardData;

import com.spo.certificate.board.dto.BoardData;
import com.spo.certificate.exam.dto.ExamData;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDataSaveService {

    private final SqlSessionTemplate sql;

    public int save(BoardData boardData){
        return sql.insert("com.spo.certificate.board.mapper.BoardDataMapper.save",boardData);
    }

}
