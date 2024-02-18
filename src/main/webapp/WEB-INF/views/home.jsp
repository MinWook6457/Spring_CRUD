<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../resources/js/jquery/jquery-3.7.1.js"></script>
	
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
    <form id="loginForm" method="post" action="/home/login">
        아이디 : <input type="text" id="user_lgn_id" name="userLoginId" required autofocus><br>
        비밀번호 : <input type="password" id="user_pw" name="userPassword" required><br>
        <input type="submit" id="loginBtn" value="로그인"> 
        <input type="button" id="registerBtn" value="회원가입" onclick="window.location.href='/user/insertUser.do';">
        <input type="button" id="findPwBtn" value="비밀번호 찾기"  onclick="redirectToFindUserInfo();">
    </form>
</body>

<script>
    $(document).ready(function() {
        $("#loginForm").submit(function(event){
            event.preventDefault();
            var userLoginData = {
                userLoginId : $('#user_lgn_id').val(),
                userPassWord : $('#user_pw').val()
            };  
            $.ajax({
                type: 'POST',
                url: "/home/login",
                contentType: 'application/json',
                data: JSON.stringify(userLoginData),
                success : function(response){
                    alert('로그인 성공');
                    console.log(response);
                    location.href = '/welcome';
                },
                error : function(xhr, status, error){
                    console.log(xhr.responseText);                    
                    alert("로그인 실패");
                }
            });
        });
    });
    
    function redirectToFindUserInfo() {
        window.location.href = '/user/findUserInfo';
    }
</script>
