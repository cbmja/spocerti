package com.spo.certificate;


import com.spo.certificate.board.dto.BoardData;
import com.spo.certificate.board.service.boardData.BoardDataSaveService;
import com.spo.certificate.exam.service.exam.ExamInfoService;
import com.spo.certificate.exam.service.examData.ExamDataSaveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class CertificateApplicationTests {

	@Autowired
	private ExamDataSaveService examDataSaveService;
	@Autowired
	private ExamInfoService examInfoService;
	@Autowired
	private BoardDataSaveService boardDataSaveService;
	@Test
	void contextLoads() {

		BoardData boardData = new BoardData();
		boardData.setTitle("제목");
		boardData.setContent("내용");
		boardData.setUserId("비회원");
		boardData.setCreatedAt(LocalDateTime.now());
		boardData.setViewCnt(0);
		//boardData.setBoardId("free");
		boardData.setLikes(0);

		boardDataSaveService.save(boardData);



	}

}
