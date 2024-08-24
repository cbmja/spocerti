
$(document).ready(function() {

/*시험 '선택'시 동적으로 년도 select 생성*/ //ok
    $('#main-exam-select').change(function() {

        //선택된 시험
        var examCode = $(this).val();


        $.ajax({
            url: '/main/exam/select',
            type: 'GET',
            data: { code: examCode } ,
            success: function(response) {

                // response는 서버에서 반환된 연도 리스트 (List<String>)
                // 시험 개설 년도 ~ db에 업데이트된 최신 년도 ex) ALL , 2010 , 2011 , 2012 ....
                var yearSelect = $('#main-year-select');

                // 기존의 모든 옵션 제거
                yearSelect.empty();

                var options = '';

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
/*시험 '선택'시 동적으로 년도 select 생성*/ //ok



/*시험 '검색' 시 목록 생성*/ //ok
    $('#main-exam-search-btn').click(function() {
        //검색 시험 코드
        var examCode = $('#main-exam-select').val();
        //검색 과목 코드
        var examYear = $('#main-year-select').val();

        if (!examCode || !examYear) {
            alert("시험, 년도를 모두 선택하세요.");
            return;
        }

        var form = {
            "examCode": examCode,
            "examYear": examYear
        };

        $.ajax({
            url: '/main/exam/search',
            type: 'GET',
            data: form,
            success: function(response) {
                var tbody = $('#exam-list');
                tbody.empty();

                if (response && response.length > 0) {
                    var rows = '';

                    $.each(response, function(index, item) {
                        rows += '<tr>' +
                                '<td style="border: 1px solid black;">' + item.examYear + '</td>' +
                                '<td style="border: 1px solid black;">' + item.examTitle + '</td>' +
                                '<td style="border: 1px solid black;">' +
                                '<select name="type" class="exam-type">' +
                                '<option value="A" selected>A</option>' +
                                '<option value="B">B</option>' +
                                '</select>' +
                                '</td>' +
                                '<td style="border: 1px solid black;">' +
                                '<form action="/main/exam/take">'+
                                '<input type="hidden" name="examCode" value='+examCode+'>'+
                                '<input type="hidden" name="examYear" value='+item.examYear+'>'+
                                '<input type="hidden" name="examType" value="A">'+
                                '<button type="submit">응시</button>' +
                                '</form>'+
                                '</td>' +
                                '</tr>';
                    });

                    tbody.append(rows);
                } else {
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
/*시험 '검색' 시 목록 생성*/ //ok




/*type 변경시 form 값 변경*/ //ok
    $('#exam-list').on('change', '.exam-type', function() {
        var selectedType = $(this).val(); // 선택된 type 값을 가져옴
        var form = $(this).closest('tr').find('form');
        form.find('input[name="examType"]').val(selectedType); // hidden 필드 값 업데이트
    });
/*type 변경시 form 값 변경*/ //ok



});