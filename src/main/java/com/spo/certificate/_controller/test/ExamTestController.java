package com.spo.certificate._controller.test;

import com.spo.certificate._dto.answer.Answer;
import com.spo.certificate._dto.answer.AnswerDetail;
import com.spo.certificate._dto.exam.Exam;
import com.spo.certificate._dto.exam.ExamSubject;
import com.spo.certificate._dto.test.TestData;
import com.spo.certificate._dto.test.TestDataDetail;
import com.spo.certificate._dto.test.TestSubjectData;
import com.spo.certificate._service.answer.AnswerDetailService;
import com.spo.certificate._service.answer.AnswerService;
import com.spo.certificate._service.exam.ExamService;
import com.spo.certificate._service.exam.ExamSubjectService;
import com.spo.certificate._service.test.TestDataDetailService;
import com.spo.certificate._service.test.TestDataService;
import com.spo.certificate._service.test.TestSubjectDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ExamTestController {

    private final ExamService examService;
    private final AnswerService answerService;
    private final AnswerDetailService answerDetailService;
    private final TestDataService testDataService;
    private final ExamSubjectService examSubjectService;
    private final TestSubjectDataService testSubjectDataService;
    private final TestDataDetailService testDataDetailService;

    @PostMapping("/test/submit")
    @ResponseBody
    public String gradingTest(@RequestParam Map<String,String> form){

        System.out.println(form+";;;;;;;;;");

        //응시한 시험 코드
        int examCode = Integer.parseInt(form.get("examCode"));
        Exam exam = examService.findByExamCode(examCode);


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
        data.setExamType(examType);
        data.setExamYear(examYear);
        data.setExamCode(examCode);
        for(int i : subjectCodes){
            data.setSubjectCode(i);
            answers.add(answerService.findByExamCodeAndSubjectCodeAndYearAndType(data));
        }


        // key = answer master 정보 - 어떤 시험의 어떤 과목인지
        // value = answer detail 정보 - 해당 시험 해당 과목의 문제번호 , 정답 정보
        Map<Integer , List<AnswerDetail>> answerDetail = new HashMap<>();

        for(Answer answer : answers){
            int answerCode = answer.getAnswerCode();
            List<AnswerDetail> answerDetails = answerDetailService.findByAnswerCode(answerCode);
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
        testData.setExamYear(examYear);
        testData.setExamType(examType);
        testData.setTotalScore(totalScore);
        testData.setTestResult(exam.getExamPassingScore() <= totalScore ? "P":"F");

        System.out.println(testData+"///////////");

        testDataService.save(testData);
        int testDatacode = testDataService.getCode();

        TestSubjectData tsd = new TestSubjectData();
        tsd.setTestDataCode(testDatacode);

        Map<Integer,Integer> subjectCodeAndTestSubjectCode = new HashMap<>();

        String res ="[ 시험 코드: "+examCode+" ] / [ 시험 년도 : "+examYear+" ] / [ 시험 타입 : "+examType+"] / [ 총점 : "+totalScore+" ] / ";
        for(Integer subCode : scores.keySet()){
            res += "[ 과목 코드 : "+subCode+" / 점수 : "+scores.get(subCode)+" ]";


            ExamSubject examSubject = new ExamSubject();
            examSubject.setExamCode(examCode);
            examSubject.setSubjectCode(subCode);
            examSubject = examSubjectService.findByExamCodeAndSubjectCode(examSubject);

            tsd.setSubjectCode(subCode);
            tsd.setTestSubjectScore(scores.get(subCode));
            tsd.setTestSubjectResult(scores.get(subCode) >= examSubject.getSubjectPassingScore() ? "P":"F");
            testSubjectDataService.save(tsd);
            subjectCodeAndTestSubjectCode.put(subCode,testSubjectDataService.getCode());
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
                    testDataDetail.setQuestionResult("T");
                }else {
                    testDataDetail.setQuestionResult("F");
                }
                testDataDetailService.save(testDataDetail);
            }

        }


        return res;




    }

}
