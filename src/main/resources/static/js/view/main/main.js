$(document).ready(function() {
    $('#main-exam-select').change(function() {
        var selectedExamCode = $(this).val();
        $.ajax({
            url: '/main/select/exam',
            type: 'GET',
            data: { code: selectedExamCode },
            success: function(response) {
                // response는 서버에서 반환된 연도 리스트 (List<String>)
                var yearSelect = $('#main-year-select');

                // 기존의 모든 옵션 제거
                yearSelect.empty();

                // 기본 선택 옵션 추가
                yearSelect.append('<option value="" disabled selected>년도</option>');

                // 반환된 연도 리스트를 반복하며 옵션 추가
                $.each(response, function(index, year) {
                    yearSelect.append('<option value="' + year + '">' + year + '</option>');
                });
            },
            error: function(error) {
                console.error("Error occurred: ", error);
            }
        });
    });
});