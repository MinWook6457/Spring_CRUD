<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../resources/js/jquery/jquery-3.7.1.js"></script>
<script src="../resources/js/jquery/jquery.validate.js"></script>

<script>
$(document).ready(function(){
		$.("#modifyUserInfoBtn").click(function(event){
			
		})
	})
}
</script>

<title>유저 정보 수정 폼</title>

<body>
	<h1> ${sessionScope.loginUser.userNm} 의 정보 수정 화면 </h1>
	<form id = "modifyUserInfo" method = post>
	<label for="login_id">로그인 ID </label> <input type="text" id="userLoginId" value=" ${sessionScope.loginUser.userLoginId}">
	<label for="user_name">닉네임 </label> <input type="text" id="user_name" value=" ${sessionScope.loginUser.userLoginId}">
	<label for="password">패스워드</label> <input type="password" id="password" value=" ${sessionScope.loginUser.userLoginId}">
	
	<input type="button" id="modifyUserInfoBtn" value="수정">
	</form>
</body>
