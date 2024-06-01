<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/include.jsp" />

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
				userLoginId : $('#loginId').val(),
	            userPassWord : $('#changePassword').val()
			}

				$.ajax({
				   type: 'POST',
	          	   url : "/user/modifyPW",
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

<body>
<div class="container mt-5">
	<div class="row justify-content-center">
		<div class="col-lg-6">
			<div class="card">
				<div class="card-header bg-primary text-white">
					비밀번호가 기억나지 않는다면 변경하세요!
				</div>
				<div class="card-body">
					<div class="mb-3">
						<label for="username" class="form-label">사용자명</label>
						<input type="text" id="username" class="form-control" value="${sessionScope.loginUser.userNm}" readonly>
					</div>
					<div class="mb-3">
						<label for="loginId" class="form-label">아이디</label>
						<input type="text" id="loginId" class="form-control" value="${sessionScope.loginUser.userLoginId}" readonly>
					</div>
					<div class="mb-3">
						<label for="changePassword" class="form-label">변경할 비밀번호</label>
						<input type="password" id="changePassword" class="form-control">
					</div>
					<div class="col-sm-12 d-flex justify-content-between">
						<button class="btn btn-outline-primary w-5 text-bg-dark mb-3 " type="button" id="modifyUserPWBtn">변경하기</button>
						<a class="btn btn-outline-primary mb-3" href="/welcome" role="button">뒤로가기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
