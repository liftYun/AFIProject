//Id 중복 체크
const idCheck = () => {
    const userId = document.getElementById("userId").value;
    const checkResult = document.getElementById("idCheckResult");
    $.ajax({
        type:"post",
        url:"/user/idCheck",
        data:{
            "userId": userId
        },
        success : function (res) {
            if(res === "ok") {
                checkResult.style.color = "green";
                checkResult.innerHTML = "사용가능한 ID";
            } else if (res === "duplicate") {
                checkResult.style.color = "red";
                checkResult.innerHTML = "이미 사용중인 ID";
            }
        },
        error: function (err) {
        }
        }
    );
};
/*function idCheck() {
    // userId input 필드에서 값 가져오기
    const id = document.getElementById("userId").value;
    // idCheckResult div 가져오기
    const checkResult = document.getElementById("idCheckResult");

    $.ajax({
        type: "POST", // HTTP 요청 방식
        url: "/user/idCheck", // 요청을 보낼 서버의 URL 주소
        data: {
            "userId": id // 서버로 보낼 데이터
        },
        success: function(res) {
            // 요청이 성공하면 실행될 함수
            if(res === "ok") {
                // 중복되지 않는 경우
                checkResult.style.color = "green";
                checkResult.innerHTML = "사용가능한 ID입니다.";
            } else if (res === "duplicate") {
                // 중복되는 경우
                checkResult.style.color = "red";
                checkResult.innerHTML = "이미 사용중인 ID입니다.";
            }
        },
        error: function(err) {
            // 요청이 실패하면 실행될 함수
            console.error("에러 발생: ", err);
        }
    });
}*/
//휴대폰번호 중복 체크
const phoneCheck = () => {
    const userPhone = document.getElementById("userPhone").value;
    const checkResult = document.getElementById("phoneCheckResult");
    $.ajax({
            type:"post",
            url:"/user/phoneCheck",
            data:{
                "userPhone": userPhone
            },
            success : function (res) {
                if(res === "ok") {
                    checkResult.style.color = "green";
                    checkResult.innerHTML = "사용가능한 전화번호";
                } else if (res === "duplicate") {
                    checkResult.style.color = "red";
                    checkResult.innerHTML = "이미 가입된 번호ㅓ";
                }
            },
            error: function (err) {
            }
        }
    );
};

