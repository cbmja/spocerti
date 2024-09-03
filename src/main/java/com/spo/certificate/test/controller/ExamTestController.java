package com.spo.certificate.test.controller;

import com.spo.certificate.test.answer.dto.Answer;
import com.spo.certificate.test.answer.dto.AnswerDetail;
import com.spo.certificate.test.answer.service.AnswerDetailInfoService;
import com.spo.certificate.test.answer.service.AnswerInfoService;
import com.spo.certificate.test.exam.dto.Exam;
import com.spo.certificate.test.exam.service.ExamInfoService;
import com.spo.certificate.test.examSubject.dto.ExamSubject;
import com.spo.certificate.test.examSubject.service.ExamSubjectInfoService;
import com.spo.certificate.test.test.dto.TestData;
import com.spo.certificate.test.test.dto.TestDataDetail;
import com.spo.certificate.test.test.dto.TestSubjectData;
import com.spo.certificate.test.test.service.TestDataDetailSaveService;
import com.spo.certificate.test.test.service.TestDataSaveService;
import com.spo.certificate.test.test.service.TestSubjectDataSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ExamTestController {

    private final AnswerInfoService answerInfoService;
    private final AnswerDetailInfoService answerDetailInfoService;
    private final ExamInfoService examInfoService;
    private final ExamSubjectInfoService examSubjectInfoService;

    private final TestDataSaveService testDataSaveService;
    private final TestSubjectDataSaveService testSubjectDataSaveService;
    private final TestDataDetailSaveService testDataDetailSaveService;


    @PostMapping("/test/submit")
    @ResponseBody
    public String gradingTest(@RequestParam Map<String,String> form){

        //응시한 시험 코드
        int examCode = Integer.parseInt(form.get("examCode"));
        Exam exam = examInfoService.findByCode(examCode);


        //응시한 시험 타입
        String examType = form.get("examType");
        //응시한 시험 년도
        int examYear = Integer.parseInt(form.get("examYear"));

        int userCode = 1;


        //응시한 과목 코드
        List<Integer> subjectCodes = new ArrayList<>();
        //제출된 폼에서 key를 통해 선택한 과목 코드 추출
        for(Map.Entry<String,String> entry : form.entrySet()){
            if(entry.getKey().contains("subjectCode-")){
                subjectCodes.add(Integer.parseInt(entry.getKey().substring(12)));
            }
        }


        //제출 답안
        Map<Integer,List<Integer>> submittedAnswer = new HashMap<>();
        for(int subjectCode : subjectCodes){
            String answers = form.get("subjectCode-"+subjectCode);
            List<Integer> answerList = Arrays.stream(answers.split("_"))
                                                .map(Integer::parseInt)
                                                .toList();
            submittedAnswer.put(subjectCode,answerList);
        }


        //answer master 정보
        List<Answer> answers = new ArrayList<>();

        Answer data = new Answer();
        data.setType(examType);
        data.setYear(examYear);
        data.setExamCode(examCode);
        for(int i : subjectCodes){
            data.setSubjectCode(i);
            answers.add(answerInfoService.findByExamCodeAndSubjectCodeAndYearAndType(data));
        }


        // key = answer master 정보 - 어떤 시험의 어떤 과목인지
        // value = answer detail 정보 - 해당 시험 해당 과목의 문제번호 , 정답 정보
        Map<Integer , List<AnswerDetail>> answerDetail = new HashMap<>();

        for(Answer answer : answers){
            int answerCode = answer.getCode();
            List<AnswerDetail> answerDetails = answerDetailInfoService.findByAnswerCode(answerCode);
            answerDetail.put(answer.getSubjectCode() , answerDetails);
        }

        System.out.println("제출 답안 : "+submittedAnswer);
        System.out.println("답안 정보 : "+answerDetail);

        //과목코드 , 점수
        Map<Integer,Integer> scores = new HashMap<>();

        int totalScore = 0;
        for(int sjCode : submittedAnswer.keySet()){

            List<Integer> submittedAnswerList = submittedAnswer.get(sjCode);
            List<AnswerDetail> answerList = answerDetail.get(sjCode);
            System.out.println("[ 과목 코드 ] : "+sjCode);

            int score = 0;

            for(AnswerDetail ans : answerList){
                int questionNo = ans.getQuestionNo(); //문제 번호
                String answerNo = ans.getAnswer()+""; //정답
                String submittedNo = submittedAnswerList.get(questionNo - 1)+""; //제출 답안
                int points = ans.getPoints(); //문제별 배점

                //제출 정답 정렬 -> 32 이면 23 , 1342 이면 1234
                if(submittedNo.length() > 1){
                    char[] digits = submittedNo.toCharArray();
                    Arrays.sort(digits);
                    submittedNo = new String(digits);
                }

                if(answerNo.equals(submittedNo)){
                    score += points;
                }
            }
            scores.put(sjCode,score);
            totalScore += score;
        }



        TestData testData = new TestData();
        testData.setUserCode(userCode);
        testData.setExamCode(examCode);
        testData.setYear(examYear);
        testData.setType(examType);
        testData.setScore(totalScore);
        testData.setResult(exam.getPassingScore() <= totalScore ? "P":"F");

        testDataSaveService.save(testData);
        int testDatacode = testDataSaveService.getCode();

        TestSubjectData tsd = new TestSubjectData();
        tsd.setTestDataCode(testDatacode);

        Map<Integer,Integer> subjectCodeAndTestSubjectCode = new HashMap<>();

        String res ="[ 시험 코드: "+examCode+" ] / [ 시험 년도 : "+examYear+" ] / [ 시험 타입 : "+examType+"] / [ 총점 : "+totalScore+" ] / ";
        for(Integer subCode : scores.keySet()){
            res += "[ 과목 코드 : "+subCode+" / 점수 : "+scores.get(subCode)+" ]";


            ExamSubject examSubject = new ExamSubject();
            examSubject.setExamCode(examCode);
            examSubject.setSubjectCode(subCode);
            examSubject = examSubjectInfoService.findByExamCodeAndSubjectCode(examSubject);

            tsd.setSubjectCode(subCode);
            tsd.setScore(scores.get(subCode));
            tsd.setResult(scores.get(subCode) >= examSubject.getPassingScore() ? "P":"F");
            testSubjectDataSaveService.save(tsd);
            subjectCodeAndTestSubjectCode.put(subCode,testSubjectDataSaveService.getCode());
            System.out.println(subjectCodeAndTestSubjectCode);
        }


        for(int sjCode : submittedAnswer.keySet()){

            //제출 답안
            List<Integer> submittedAnswerList = submittedAnswer.get(sjCode);
            //진짜 답안지
            List<AnswerDetail> answerList = answerDetail.get(sjCode);
            //testSubCode -> testDetailMaster
            int testSubCode = subjectCodeAndTestSubjectCode.get(sjCode);

            TestDataDetail testDataDetail = new TestDataDetail();
            testDataDetail.setTestSubjectDataCode(testSubCode);

            for(AnswerDetail ans : answerList){
                int questionNo = ans.getQuestionNo(); //문제 번호
                String answerNo = ans.getAnswer()+""; //정답
                String submittedNo = submittedAnswerList.get(questionNo - 1)+""; //제출 답안

                testDataDetail.setQuestionNo(questionNo);
                testDataDetail.setSubmittedAnswer(Integer.parseInt(submittedNo));
                //제출 정답 정렬 -> 32 이면 23 , 1342 이면 1234
                if(submittedNo.length() > 1){
                    char[] digits = submittedNo.toCharArray();
                    Arrays.sort(digits);
                    submittedNo = new String(digits);
                }

                if(answerNo.equals(submittedNo)){
                    testDataDetail.setResult("T");
                }else {
                    testDataDetail.setResult("F");
                }
                testDataDetailSaveService.save(testDataDetail);
            }

        }


        return res;




    }


}
