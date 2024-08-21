// 선택된 과목을 저장할 배열
let selectedSubjects = [];

// 최대 선택 가능한 과목 수를 데이터 속성에서 가져오기
const maxSelections = parseInt(document.getElementById('electiveSubjectCnt').dataset.cnt, 10);

// 클래스 이름으로 체크박스 요소들을 가져옴
const checkboxes = document.getElementsByClassName('electiveSubject');

// HTMLCollection을 배열로 변환하고, 각 요소에 이벤트 리스너 추가
Array.from(checkboxes).forEach(function(checkbox) {
    checkbox.addEventListener('change', function() {
        const subjectId = checkbox.value;

        if (checkbox.checked) {
            if (selectedSubjects.length < maxSelections) {
                // 체크박스가 선택되었을 때 배열에 추가
                selectedSubjects.push(subjectId);
            } else {
                // 최대 선택 수를 초과하면 선택을 취소하고 경고 메시지 표시
                checkbox.checked = false;
                alert('최대 ' + maxSelections + '개의 과목만 선택할 수 있습니다.');
            }
        } else {
            // 체크박스가 해제되었을 때 배열에서 제거
            selectedSubjects = selectedSubjects.filter(id => id !== subjectId);
        }

        console.log('현재 선택된 과목:', selectedSubjects);
    });
});



window.onload = function() {
    document.getElementById('grading-btn').addEventListener('click', function(event) {
        event.preventDefault(); // 폼 제출 막기

        // 맵 객체 생성
        const formData = new Map();

        // grading-form 안의 모든 input 요소와 textarea 요소 선택
        const form = document.getElementById('grading-form');
        if (!form) {
            console.error('grading-form이 존재하지 않습니다.');
            return;
        }

        const formElements = form.elements;

        // formElements가 제대로 가져와졌는지 확인
        if (!formElements) {
            console.error('formElements가 정의되지 않았습니다.');
            return;
        }

        // for 루프를 사용해 요소 순회
        for (let i = 0; i < formElements.length; i++) {
            const element = formElements[i];

            if (element.tagName === 'INPUT' && element.type === 'hidden') {
                // input 요소의 name을 key로 하고 value를 value로 저장
                formData.set(element.name, element.value);
            } else if (element.tagName === 'TEXTAREA') {
                // textarea의 data-subjectId를 key로 하고 value를 value로 저장
                const subjectId = element.getAttribute('data-subjectId');
                if (subjectId) {
                    formData.set(subjectId, element.value);
                }
            }
        }

        // 맵에 저장된 데이터 확인용 출력
        for (let [key, value] of formData) {
            console.log(key + ' = ' + value);
        }
        // formData를 JSON 객체로 변환
        const jsonObject = Object.fromEntries(formData);

        // Fetch API를 사용해 AJAX 요청 보내기
        fetch('/exam/grading', {
            method: 'POST', // POST 요청
            headers: {
                'Content-Type': 'application/json', // JSON 형식으로 전송
            },
            body: JSON.stringify(jsonObject) // JSON 문자열로 변환한 데이터 전송
        })
        .then(response => response.json()) // 응답을 JSON으로 파싱
        .then(data => {
            console.log('서버로부터의 응답:', data);
            // 서버 응답에 따라 추가 작업 수행
        })
        .catch(error => {
            console.error('요청 중 오류 발생:', error);
        });

    });
};



document.getElementById('take-exam').addEventListener('click', function() {
    // 배열을 생성하여 체크된 항목의 값을 저장
    const selectedSubjects = [];

    // class="requiredSubject"에서 체크된 항목의 값을 수집
    const requiredCheckboxes = document.querySelectorAll('.requiredSubject:checked');
    requiredCheckboxes.forEach(function(checkbox) {
        selectedSubjects.push(checkbox.value);
    });

    // class="electiveSubject"에서 체크된 항목의 값을 수집
    const electiveCheckboxes = document.querySelectorAll('.electiveSubject:checked');
    electiveCheckboxes.forEach(function(checkbox) {
        selectedSubjects.push(checkbox.value);
    });

    // 결과 출력 (디버깅용)
    console.log('선택된 과목들의 ID:', selectedSubjects);

    // 수집한 과목의 수만큼 textarea 생성
    const examQuestionArea = document.getElementById('exam_question_area');
    examQuestionArea.innerHTML = ''; // 기존의 내용을 모두 지움

    selectedSubjects.forEach(function(subjectId) {
        // div 요소 생성
        const div = document.createElement('div');
        div.setAttribute('data-subjectId', subjectId);
        // textarea 요소 생성
        const textarea = document.createElement('textarea');
        textarea.setAttribute('data-subjectId', subjectId);
        textarea.id = 'grading-subject-' + subjectId;
        textarea.placeholder = 'Subject ID: ' + subjectId;

        // 생성한 textarea를 div에 추가
        div.appendChild(textarea);

        // 생성한 div를 exam_question_area에 추가
        examQuestionArea.appendChild(div);
    });
});


