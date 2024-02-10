<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 폼</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#registerForm').submit(function(e){
                e.preventDefault();
                var formData = {
                    userLoginId: $('#login_id').val(),
                    userNm: $('#user_name').val(),
                    password: $('#password').val(),
                    sex: $('#sex').val(),
                    dateOfBirth: $('#birth').val(),
                    hintAnswer: $('#hint_comment').val() 
                };
                
                $.ajax({
                    type: 'POST',
                    url: '/inco/user/insertUser.json',
                    contentType: 'application/json',
                    data: JSON.stringify(formData),
                    success: function(response){
                        console.log(response);
                        alert('회원가입이 완료되었습니다.');
                        location.href = '/inco/login'; // 로그인 페이지로 이동
                    },
                    error: function(xhr, status, error){
                        console.log(xhr.responseText);
                        alert('회원가입에 실패했습니다. 다시 시도해주세요.');
                    }
                });
            });
        });
    </script>
</head>
<body>
    <h2>회원가입 폼</h2>
    <form id="registerForm" method="POST">
        <label for="login_id">로그인 ID:</label> <input type="text" id="login_id" name="userLoginId" required /> <br> <br> 
        <label for="user_name">사용자 이름:</label> <input type="text" id="user_name" name="userNm" required> <br> <br> 
        <label for="password">비밀번호:</label> <input type="password" id="password" name="password" required> <br> <br> 
        <label for="sex">성별:</label> 
        <select id="sex" name="sex">
            <option value="M">남성</option>
            <option value="F">여성</option>
        </select> <br> <br> 
        <label for="birth">생년월일:</label> <input type="date" id="birth" name="dateOfBirth" required> <br> <br> 
        <label for="hint_comment">비밀번호 힌트:</label> <input type="text" id="hint_comment" name="hintAnswer"> <br> <br> 
        <input type="submit" value="가입하기">
    </form>
</body>
</html>
