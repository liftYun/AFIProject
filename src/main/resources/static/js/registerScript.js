//Id 중복 체크
document.addEventListener('DOMContentLoaded', function() {
    var userIdInput = document.getElementById('userId');
    if (userIdInput) {
        userIdInput.addEventListener('keyup', function() {
            var userId = this.value;
            // 유저가 입력한 ID가 8자 이상일 때만 검증
            if(userId.length >= 8){
                fetch('/user/idCheck', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ userId: userId })
                })
                    .then(response => response.json()) // JSON 응답을 처리하도록 수정
                    .then(data => {
                        var resultElement = document.getElementById('userIdCheckResult');
                        resultElement.innerText = data.message; // 메시지 업데이트
                        // 상태에 따른 색상 변경
                        if (data.status === 'success') {
                            resultElement.style.color = 'green';
                            document.getElementById('submitButton').disabled = false;
                        } else {
                            resultElement.style.color = 'red';
                            document.getElementById('submitButton').disabled = true;
                        }
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            } else {
                // 입력 길이가 8자 미만일 경우의 처리
                var resultElement = document.getElementById('userIdCheckResult');
                resultElement.innerText = 'ID는 8자 이상, 20자 이하 이어야 합니다.'; // 길이 경고 메시지
                resultElement.style.color = 'red'; // 색상 변경
                document.getElementById('submitButton').disabled = true;
            }
        });
    }
});
//휴대폰번호 중복 체크
document.addEventListener('DOMContentLoaded', function() {
    var userPhoneInput = document.getElementById('userPhone');
    if (userPhoneInput) {
        userPhoneInput.addEventListener('keyup', function() {
            var userPhone = this.value;

            if(userPhone.length === 11){
                fetch('/user/phoneCheck', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ userPhone: userPhone })
                })
                    .then(response => response.json()) // JSON 응답을 처리하도록 수정
                    .then(data => {
                        var resultElementP = document.getElementById('userPhoneCheckResult');
                        resultElementP.innerText = data.message; // 메시지 업데이트
                        // 상태에 따른 색상 변경
                        if (data.status === 'success') {
                            resultElementP.style.color = 'green';
                            document.getElementById('submitButton').disabled = false;
                        } else {
                            resultElementP.style.color = 'red';
                            document.getElementById('submitButton').disabled = true;
                        }
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            } else if(userPhone.length > 11){
                var resultElement = document.getElementById('userPhoneCheckResult');
                resultElement.innerText = '전화번호를 다시 확인해 주세요.'; // 길이 경고 메시지
                resultElement.style.color = 'red'; // 색상 변경
                document.getElementById('submitButton').disabled = true;
            }
        });
    }
});