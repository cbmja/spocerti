package com.spo.certificate;

import com.spo.certificate.exam.service.subject.SubjectService;
import com.spo.certificate.exam.service.exam.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CertificateApplicationTests {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ExamService examService;


	@Test
	void contextLoads() {



		System.out.println(subjectService.findById(1));
		System.out.println(examService.findById(1));

	}

}
