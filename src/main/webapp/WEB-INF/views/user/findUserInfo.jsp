<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../resources/js/jquery/jquery-3.7.1.js"></script>
<script src="../resources/js/jquery/jquery.validate.js"></script>

<title>비밀번호 찾기 페이지</title>


<script>
	$(document).ready(function() {
		$('#findUserInfoBtn').click(function(event){	
			event.preventDefault();
			 var userLoginId = $('#userLoginId').val();
	         var hintData = $('#hint_question').val().split(","); // 힌트 질문의 값에서 힌트 번호와 힌트 내용을 추출
	         
	         var pswdHintSn = hintData[0];
	         var hintComment = hintData[1];
	         
	         var hintAnswer = $('#hint_comment').val();
	         
	         var findUserData = {
	                 userLoginId : userLoginId,
	                 pswdHintSn : pswdHintSn,
	                 hintAnswer : hintAnswer
	             }
			
			$.ajax({
	               type: 'POST',
	               url : "/user/findUserPW",
	               contentType: 'application/json',
	               data: JSON.stringify(findUserData),
	               success: function(response) {	                    
						alert("당신의 비밀번호는 " + response)
						location.href = '/home';
	               },
	               error: function(xhr, status, error) {
	            	    alert("아이디가 존재하지 않거나 질문과 답변이 일치하지 않습니다.")
	            	    location.href = '/home';
                   }
	        });
		})		
	})
</script>
<form id="findUserForm" action="/user/findUserPW" method='post'>
	<label for="userLoginId">아이디를 입력해주세요:</label> <input type="text" id="userLoginId" name="userLoginId" required> 
	<select id="hint_question" name="hintQuestion">
		<c:forEach var="hintQuestion" items="${hintQuestions}">
			<option value="${hintQuestion.pswdHintSn} , ${hintQuestion.hintCn}">${hintQuestion.hintCn}</option>
		</c:forEach>
	</select> 
	<br><br> 
	<label for="hint_comment">비밀번호 힌트 답변 : </label> 
	<input type="text" id="hint_comment" name="hintAnswer"> 
	<input type="button" id="findUserInfoBtn" value="입력">
</form>

