package com.spo.certificate;

import com.spo.certificate.exam.dto.ExamData;
import com.spo.certificate.exam.service.examData.ExamDataSaveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CertificateApplicationTests {

	@Autowired
	private ExamDataSaveService examDataSaveService;

	@Test
	void contextLoads() {
		for(int i= 2015; i<=2024; i++){
			ExamData examData = new ExamData();


		}


	}

}
