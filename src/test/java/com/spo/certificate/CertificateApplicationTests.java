package com.spo.certificate;

import com.spo.certificate.exam.service.examSubject.ExamSubjectService;
import com.spo.certificate.exam.service.examTitle.ExamTitleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CertificateApplicationTests {

	@Autowired
	private ExamSubjectService examSubjectService;

	@Autowired
	private ExamTitleService examTitleService;


	@Test
	void contextLoads() {



		System.out.println(examSubjectService.findById(1));
		System.out.println(examTitleService.findById(1));

	}

}
