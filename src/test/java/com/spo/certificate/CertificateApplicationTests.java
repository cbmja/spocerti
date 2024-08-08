package com.spo.certificate;

import com.spo.certificate.exam.dto.Exam;
import com.spo.certificate.exam.dto.ExamData;
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

		int k = 1;
		for(int i= 2015; i<=2020; i++){
/*
			ExamData examData = new ExamData();
			examData.setId(k);
			examData.setExamTitle("1급_전문스포츠지도사");
			examData.setYear(i);
			examData.setExamId(1);
			examData.setType("A");
			examData.setPath("C:\\Project\\sideproject\\spocert\\생체data\\data\\test\\1급_스포츠지도사\\"+i);
			examData.setFileName(i+examData.getExamTitle()+"필기"+examData.getType()+".pdf");


			ExamData examData2 = new ExamData();
			examData2.setId(k);
			examData2.setExamTitle("1급_전문스포츠지도사");
			examData2.setYear(i);
			examData2.setExamId(1);
			examData2.setType("B");
			examData2.setPath("C:\\Project\\sideproject\\spocert\\생체data\\data\\test\\1급_스포츠지도사\\"+i);
			examData2.setFileName(i+examData.getExamTitle()+"필기"+examData.getType()+".pdf");
			examDataSaveService.createExamData(examData);
			examDataSaveService.createExamData(examData2);
*/

			System.out.println(examInfoService.findById(1));
		}


	}

}
