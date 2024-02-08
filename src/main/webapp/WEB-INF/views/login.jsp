<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> <!-- jQuery 라이브러리 추가 -->
    <script>
        $(document).ready(function() {
            $("#registerBtn").click(function() {
                window.location.href = "/inco/user/insertUser.do"; // 버튼 클릭 시 /user/insertUser.do 경로로 이동
            });
        });
    </script>
</head>
<body>
    <form name="login" method="post" action="login">
        아이디 : <input type="text" id="user_id" name="id" required autofocus required><br>
        비밀번호 : <input type="password" id="user_pw" name="pw" required><br>
        <input type="submit" value="로그인"> 
        <input type="button" id="registerBtn" value="회원가입"> 
    </form>
</body>
</html>
