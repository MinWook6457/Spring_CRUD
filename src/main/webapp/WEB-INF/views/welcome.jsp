<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<title>환영합니다.</title>
<body>
	<h1> ${sessionScope.loginUser.userNm} 님 환영합니다! </h1>
	<input type="button" id="modifyBtn" value="회원 정보 수정" onclick="window.location.href='/user/modifyUserInfo.do';">
    <!--  
    <input type="button" id="deleteBtn" value="탈퇴하기"  onclick="window.location.href">
    -->
</body>