function hideDetail() {
    var detailBlock = document.getElementById('board-detail');
    var formBlock = document.getElementById('board-form');
    if (detailBlock) {
        detailBlock.style.display = 'none';
        formBlock.style.display = 'block';
    }
}

function hideForm() {
    var formBlock = document.getElementById('board-form');
    var detailBlock = document.getElementById('board-detail');
    if (formBlock) {
        formBlock.style.display = 'none';
        detailBlock.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    var rows = document.querySelectorAll('.board-detail-row');

    rows.forEach(function(row) {
        row.addEventListener('click', function() {
            hideForm();
        });
    });
});

document.addEventListener('DOMContentLoaded', function() {
    var rows = document.querySelectorAll('.board-detail-row');

    rows.forEach(function(row) {
        row.addEventListener('click', function() {
            var id = row.getAttribute('data-id');

            // Ajax 요청으로 게시물 상세 정보 가져오기
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/board/detail/' + id, true); // 서버에서 게시물 상세 정보를 제공하는 URL
            xhr.setRequestHeader('Content-Type', 'application/json');

            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var boardData = JSON.parse(xhr.responseText);

                    // board-detail 섹션에 데이터 채우기
                    document.getElementById('board-detail-title').textContent = '제목 : ' + boardData.title;
                    document.getElementById('board-detail-poster').textContent = '글쓴이 : ' + boardData.userId;
                    document.getElementById('board-detail-content').textContent = boardData.content;

                    console.log("ggggg");
/*                    // board-list 숨기기
                    document.querySelector('.board-list-ar').style.display = 'none';

                    // board-detail 보이기
                    document.getElementById('board-detail').style.display = 'block';*/
                } else if (xhr.readyState === 4) {
                    console.error('Error loading board detail:', xhr.statusText);
                    console.log("ddddd");
                }
            };

            xhr.send();
        });
    });
});






document.addEventListener('DOMContentLoaded', function() {
    var form = document.getElementById('board-form');
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // 기본 폼 제출 방지

        // 폼 데이터 수집
        var formData = {
            title: document.getElementById('board-title').value,
            userId: document.getElementById('userId').value,
            content: document.querySelector('.board-content').value,
            password: document.getElementById('board-poster-password').value,
            boardType: document.getElementById('boardType').value
        };

        if (!formData.title || formData.title.trim() === "") {
            alert('제목을 입력하세요');
            return;
        }
        if (!formData.password || formData.password.trim() === "") {
            alert('비밀번호를 입력하세요');
            return;
        }

        // Ajax 요청 생성
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/board/save', true); // 서버의 엔드포인트 URL
        xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // 성공 처리 로직
                    alert('글이 성공적으로 저장되었습니다.');
                    form.reset(); // 폼 초기화
                } else {
                    // 에러 처리 로직
                    alert('오류가 발생했습니다. 다시 시도해 주세요.');
                    console.error(xhr.statusText);
                }
            }
        };

        // 폼 데이터를 JSON 문자열로 변환하여 전송
        xhr.send(JSON.stringify(formData));
    });
});