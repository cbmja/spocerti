window.onload=function(){
    examId = "1";

    var selectElement = document.getElementById('examSelect');
    selectElement.value = examId;

    if(examId === "0") {
        alert("시험을 선택하세요.");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/main/subject?examId=" + examId, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            updatePage(response);
        }
    };
    xhr.send();
}
document.getElementById('examSelect2').addEventListener('change',function(){

        var examId = document.getElementById('examSelect2').value;

        var selectElement = document.getElementById('examSelect');
            selectElement.value = examId;

        document.getElementById('main-subject-search-btn').click();
});

document.getElementById('main-subject-search-btn').addEventListener('click', function() {
    var examId = document.getElementById('examSelect').value;
    console.log(examId);
    if(examId === "0") {
        alert("시험명을 선택하세요.");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/main/subject?examId=" + examId, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            updatePage(response);
        }
    };
    xhr.send();

});
function updatePage(data) {
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


