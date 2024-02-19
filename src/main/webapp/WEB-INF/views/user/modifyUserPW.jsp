<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../resources/js/jquery/jquery-3.7.1.js"></script>
<script src="../resources/js/jquery/jquery.validate.js"></script>

<script>
$(document).ready(function(){
		$("#modifyUserPWForm").validate({
			rules: {
				 password: {
		                required: true,
		                minlength: 8,
		                remote: {
		                    url: '/user/checkPassWord',
		                    type: 'POST',
		                    data: {
		                        password: function() {
		                            return $('#password').val();
		                        }
		                    }
		                }
		            },
		         messages: {
		        	  password: {
		                  remote: '비밀번호는 숫자, 영문, 특수문자를 포함하여 최소 8자리 이상이어야 합니다.',
		                  minlength: '비밀번호는 최소 8자 이상이어야 합니다.'
		              }
		         },
		         submitHandler: function(form){
		             console.log("submit!");
		             console.log(form);
		             form.submit(); // 폼 제출
		         }
			}
		});
		$('#modifyUserPWBtn').click(function(event)	{
			var userModifyData = {
				userLoginId : $('#userLoginId').val(),
	            userPassWord : $('#password').val()
			}

				$.ajax({
				   type: 'POST',
	          	   url : "/user/modifyUserPW",
	          	   contentType: 'application/json',
	               data: JSON.stringify(userModifyData),
					 success : function(res){
						console.log(res);
						alert("비밀번호 변경 완료");
						location.href="/home";
					 },
					 error : function(res){
						alert("비밀번호 변경 실패")
					 }				 
				})
	 	   })
	})
</script>

<title>비밀 번호 수정 폼</title>

<body>
	<h1> ${sessionScope.loginUser.userNm}님의 비밀번호 수정</h1>
	<form id = "modifyUserPWForm" method = post >
	<label for="login_id">로그인 ID </label> <input type="text" id="userLoginId" value="${sessionScope.loginUser.userLoginId}" readonly >
	<br><br> 
	<label for="user_name">닉네임 </label> <input type="text" id="user_name" value="${sessionScope.loginUser.userLoginId}" readonly >
	<br><br> 
	<label for="password">패스워드</label> <input type="password" id="password" value="${sessionScope.loginUser.password}">
	<input type="button" id="modifyUserPWBtn" value="수정">
	</form>
</body>
