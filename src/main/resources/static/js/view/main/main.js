
/* 처음 기본값 (mainSearch examId 기본값 1 - 1급_전문스포츠지도사)*/
window.onload=function(){
    var examSelect1 = document.getElementById('examSelect1').value;
    var examType2 = document.getElementById('examType2').value;

    var subjectSelect1 = document.getElementById('subjectSelect1');
    subjectSelect1.value = examSelect1;

    if(subjectSelect1 === "0") {
        alert("시험을 선택하세요.");
        return;
    }

    document.getElementById('main-subject-search-btn').click();

    document.getElementById('main-exam-search-btn2').click();
}


document.getElementById('examSelect1').addEventListener('change',function(){

        var examSelect1 = document.getElementById('examSelect1').value;

        var selectElement = document.getElementById('subjectSelect1');
            selectElement.value = examSelect1;

        document.getElementById('main-subject-search-btn').click();
});

document.getElementById('examSelect2').addEventListener('change',function(){

        var examSelect2 = document.getElementById('examSelect2').value;

        var subjectSelect1 = document.getElementById('subjectSelect1');
            subjectSelect1.value = examSelect2;

        document.getElementById('main-subject-search-btn').click();
});

document.getElementById('main-subject-search-btn').addEventListener('click', function() {
    var subjectSelect1 = document.getElementById('subjectSelect1').value;
    if(subjectSelect1 === "0") {
        alert("시험명을 선택하세요.");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/main/subject?examId=" + subjectSelect1, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            updateSubjectTable(response);
        }
    };
    xhr.send();

});
function updateSubjectTable(data) {
    // 시험 제목 업데이트
    var examTitleElement = document.getElementById('examTitle');
    if (examTitleElement) {
        examTitleElement.innerText = data.examTitle + "의 응시 과목 입니다.";
    }

    // 필수 과목 및 선택 과목 수 업데이트
    var requiredSubjectCountElement = document.getElementById('requiredSubjectCount');
    if (requiredSubjectCountElement) {
        requiredSubjectCountElement.innerText = data.requiredSubjects.length + " 과목";
    }

    var electiveSubjectCountElement = document.getElementById('electiveSubjectCount');
    if (electiveSubjectCountElement) {
        electiveSubjectCountElement.innerText =data.electiveCnt + " 과목("+data.electiveSubjects.length+"중 택"+data.electiveCnt+")";
    }

    // 과목 테이블 업데이트
    var tableBody = document.getElementById('subjectTableBody');
    if (tableBody) {
        let tableContent = ''; // 모든 테이블 내용을 문자열로 저장

        for (var i = 0; i < data.examSubjectList.length; i += 4) {
            tableContent += '<tr>'; // 새로운 행 시작
            for (var j = i; j < i + 4 && j < data.examSubjectList.length; j++) {
                let cellContent = data.examSubjectList[j].subjectTitle;
                let spanContent = '';
                let className = '';

                // 필수 과목 또는 선택 과목에 따른 span 및 class 추가
                if (data.requiredSubjects.includes(data.examSubjectList[j].id)) {
                    className = 'required-subject';
                    spanContent = '<span class="required-box">필수</span>';
                } else if (data.electiveSubjects.includes(data.examSubjectList[j].id)) {
                    className = 'elective-subject';
                    spanContent = '<span class="elective-box">선택</span>';
                }

                tableContent += `<td class="${className}">${cellContent}${spanContent}</td>`;
            }
            tableContent += '</tr>'; // 행 끝
        }

        // 모든 내용을 한 번에 tableBody에 추가
        tableBody.innerHTML = tableContent;
    }
}



document.getElementById('main-exam-search-btn1').addEventListener('click', function() {

    var examType1 = document.getElementById('examType1').value;
    var examYear = document.getElementById('examYear').value;
    var examSelect1 = document.getElementById('examSelect1').value;


    if(subjectSelect1 === "0") {
        alert("시험명을 선택하세요.");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/main/exam?examId=" + examSelect1+"&type="+examType1+"&year="+examYear, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            /* 화면 업데이트 하는 메서드 */
            updateExamList(response);
        }
    };
    xhr.send();

});

document.getElementById('main-exam-search-btn2').addEventListener('click', function() {

    var examType2 = document.getElementById('examType2').value;
    var examSelect2 = document.getElementById('examSelect2').value;


    if(subjectSelect1 === "0") {
        alert("시험명을 선택하세요.");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/main/exam?examId=" + examSelect2+"&type="+examType2, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            /* 화면 업데이트 하는 메서드 */
            updateExamList(response);
        }
    };
    xhr.send();

});


function updateExamList(data) {
var tableBody = document.getElementById('examListBody');
    if (tableBody) {
        let tableContent = ''; // 모든 테이블 내용을 문자열로 저장

        for (var i = 0; i < data.examDataList.length; i++) {
            tableContent += "<tr>"
                            +"<td>"+data.examDataList[i].year+"</td>"
                            +"<td class='main-exam-filename'>"+data.examDataList[i].fileName+"</td>"
                            +"<td>"+data.examDataList[i].type+"</td>"
                            +"<td> <button type='button'>모의시험</button> </td>"
                            +"<td> <a href='#'>문제</a> / <a href='#'>정답</a> </td>"
                            +"</tr>";

        }
        tableBody.innerHTML = tableContent;
    }
}


