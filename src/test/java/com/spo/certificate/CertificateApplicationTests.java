package com.spo.certificate;


import com.spo.certificate.exam.service.exam.ExamInfoService;
import com.spo.certificate.exam.service.examData.ExamDataSaveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CertificateApplicationTests {

	@Autowired
	private ExamDataSaveService examDataSaveService;
	@Autowired
	private ExamInfoService examInfoService;

	@Test
	void contextLoads() {


/*
		for(int i= 2021; i<=2024; i++){
			ExamData examData = new ExamData();
			String fileName = i+"_2급_스포츠지도사_"+"필기_";
			examData.setExamTitle("2급_장애인스포츠지도사");
			examData.setYear(i);
			examData.setExamId(9);
			examData.setType("A");
			examData.setPath("C:\\Project\\sideproject\\spocert\\생체data\\data\\test\\2급_스포츠지도사(유소년,노인)\\"+i);
			examData.setFileName(fileName+examData.getType()+"형.pdf");

			ExamData examData2 = new ExamData();
			examData2.setExamTitle(examData.getExamTitle());
			examData2.setYear(i);
			examData2.setExamId(examData.getExamId());
			examData2.setType("B");
			examData2.setPath(examData.getPath());
			examData2.setFileName(fileName+examData2.getType()+"형.pdf");
			examDataSaveService.createExamData(examData);
			examDataSaveService.createExamData(examData2);
		}
*/


	}

}
