function checkLogin(){
// 세션에 저장된 loginUser 데이터 확인
    const loginUser = JSON.parse(sessionStorage.getItem('loginUser'));

// 로그인 데이터가 없으면 로그인 페이지로 이동
    if (!loginUser) {
        location.href = '/user/loginError'; // 로그인 페이지 URL
    }
}
