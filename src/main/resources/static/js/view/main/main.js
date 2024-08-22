
/*시험 '선택'시 동적으로 년도 select 생성*/
$(document).ready(function() {
    $('#main-exam-select').change(function() {
        var selectedExamCode = $(this).val();
        $.ajax({
            url: '/main/exam/select',
            type: 'GET',
            data: { code: selectedExamCode },
            success: function(response) {
                // response는 서버에서 반환된 연도 리스트 (List<String>)
                var yearSelect = $('#main-year-select');

                // 기존의 모든 옵션 제거
                yearSelect.empty();

                // 기본 선택 옵션 추가
                var options = '<option value="" disabled selected>년도</option>';

                // 반환된 연도 리스트를 반복하며 옵션 추가
                $.each(response, function(index, year) {
                    options += '<option name="year" value="' + year + '">' + year + '</option>';
                });

                // 조합된 옵션을 한 번에 추가
                yearSelect.append(options);
            },
            error: function(error) {
                console.error("Error occurred: ", error);
            }
        });
    });
});

/*시험 '선택'시 동적으로 년도 select 생성*/


/*시험 '검색'시 목록 생성*/
$(document).ready(function() {
    $('#main-exam-search-btn').click(function() {
        // 선택된 값들을 가져옴
        var examCode = $('#main-exam-select').val();
        var year = $('#main-year-select').val();

        // 데이터가 제대로 선택되지 않은 경우 경고
        if (!examCode || !year) {
            alert("시험, 년도를 모두 선택하세요.");
            return;
        }

        var form = {
                    "examCode": examCode,
                    "year": year
                    };

        // AJAX 요청으로 서버에서 데이터를 가져옴
        $.ajax({
            url: '/main/exam/search',
            type: 'GET',
            data: form,
            success: function(response) {
                // 기존의 tbody 내용을 비움
                var tbody = $('#exam-list');
                tbody.empty();

                // 새로운 데이터를 추가
                if (response && response.length > 0) {
                    var rows = ''; // 문자열을 저장할 변수

                    $.each(response, function(index, item) {
                        rows += '<tr>' +
                                '<td style="border: 1px solid black;">' + item.year + '</td>' +
                                '<td style="border: 1px solid black;">' + item.examTitle + '</td>' +
                                '<td style="border: 1px solid black;">' +
                                '<select name="type"><option value="1">Type 1</option><option value="2">Type 2</option></select>' +
                                '</td>' +
                                '<td style="border: 1px solid black;"><button type="button">응시</button></td>' +
                                '</tr>';
                    });

                    // 조합된 문자열을 한 번에 append
                    $('#exam-list').append(rows);
                } else {
                    // 결과가 없을 경우 표시할 내용
                    var noResultRow = '<tr><td colspan="4" style="border: 1px solid black;">검색 결과가 없습니다.</td></tr>';
                    tbody.append(noResultRow);
                }
            },
            error: function(error) {
                console.error("Error occurred:", error);
                alert("데이터를 가져오는 중 오류가 발생했습니다.");
            }
        });
    });
});
/*시험 '검색'시 목록 생성*/