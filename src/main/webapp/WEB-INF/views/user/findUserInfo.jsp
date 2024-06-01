<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/include.jsp" />
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

<div class="container d-flex justify-content-center align-items-center vh-100">
	<div class="card p-4">

		<form id="findUserForm" action="/user/findUserPW" method='post'>
			<div class="mb-3">
				<label for="userLoginId" class="form-label">아이디를 입력해주세요:</label>
				<input type="text" id="userLoginId" name="userLoginId" class="form-control" required>
			</div>
			<div class="mb-3">
				<label for="hint_question" class="form-label">힌트 질문을 선택해주세요:</label>
				<select id="hint_question" name="hintQuestion" class="form-select">
					<c:forEach var="hintQuestion" items="${hintQuestions}">
						<option value="${hintQuestion.pswdHintSn},${hintQuestion.hintCn}">${hintQuestion.hintCn}</option>
					</c:forEach>
				</select>
			</div>
			<div class="mb-3">
				<label for="hint_comment" class="form-label">비밀번호 힌트 답변:</label>
				<input type="text" id="hint_comment" name="hintAnswer" class="form-control" required>
			</div>
			<div class="text-center d-flex justify-content-between" >
				<input type="button" id="findUserInfoBtn" value="입력" class="btn btn-primary">
				<a class="btn btn-outline-primary" href="/home" role="button">뒤로가기</a>
			</div>
		</form>
	</div>
</div>


