package com.spo.certificate.exam.service.examTitle;

import com.spo.certificate.exam.dto.ExamTitle;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamTitleService {
    private final SqlSessionTemplate sql;
    public ExamTitle findById(int id){

        return sql.selectOne("com.spo.certificate.exam.mapper.ExamTitleMapper.findById",id);
    }
}
