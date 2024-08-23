$(document).ready(function() {



    // 과목 선택 완료 버튼 클릭 시
    $('#select-subject-btn').click(function() {
        // 기존의 textarea를 모두 제거
        $('#exam-textarea-container').empty();

        var vitalElectiveSubjectCnt = parseInt($(this).data("elecnt")); // 필수 선택 개수
        var selectedElectiveSubjects = $('input[name="electiveSubjectCode"]:checked').length;
        console.log(vitalElectiveSubjectCnt);
        // 선택된 과목의 개수가 요구된 수와 일치하는지 확인
        if (selectedElectiveSubjects !== vitalElectiveSubjectCnt) {
            alert('선택 과목은 정확히 ' + vitalElectiveSubjectCnt + '개를 선택해야 합니다.');
            return;
        }

        // 필수 과목 처리 (이미 체크된 상태로 고정)
        $('input[name="requiredSubjectCode"]:checked').each(function() {
            var subjectCode = $(this).val();
            var subjectTitle = $(this).closest('span').text().trim();
            addTextarea(subjectCode, subjectTitle);
        });

        // 선택 과목 처리
        $('input[name="electiveSubjectCode"]:checked').each(function() {
            var subjectCode = $(this).val();
            var subjectTitle = $(this).closest('span').text().trim();
            addTextarea(subjectCode, subjectTitle);
        });

        alert('과목 선택이 완료되었습니다.');
    });

    function addTextarea(subjectCode, subjectTitle) {
        var container = $('#exam-textarea-container');
        // 이미 존재하는지 확인 후 textarea 생성
        if ($('#textarea-' + subjectCode).length === 0) {
            var textarea = '<div id="subject-' + subjectCode + '"><label>' + subjectTitle + '</label><br><textarea name="subjectText" rows="4" cols="50" data-subjectcode="'+subjectCode+'"></textarea><br></div>';
            container.append(textarea);
        }
    }



});
