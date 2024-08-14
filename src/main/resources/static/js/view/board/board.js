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
    var form = document.getElementById('board-form');
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // 기본 폼 제출 방지

        // 폼 데이터 수집
        var formData = {
            title: document.getElementById('board-title').value,
            poster: document.getElementById('board-poster').value,
            content: document.querySelector('.board-content').value,
            password: document.getElementById('board-poster-password').value
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