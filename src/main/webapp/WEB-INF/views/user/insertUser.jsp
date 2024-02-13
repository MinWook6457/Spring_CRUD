<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../resources/js/jquery/jquery-3.7.1.js"></script>
<script src="../resources/js/jquery/jquery.validate.js"></script>


<title>회원가입 폼</title>

<script>
    $(document).ready(function() {
        $('#registerForm').validate({
            rules: {
                userLoginId: {
                    required: true,
                    minlength: 2,
                    remote: {
                        url: '/inco/user/checkDuplicateId',
                        type: 'POST',
                        data: {
                            userLoginId: function(res) {
                                return $('#login_id').val();
                            }
                        }
                    }
                },
                password : {
                    required : true,
                    minlength : 8,
                    remote: {
                        url: '/inco/user/checkPassWord',
                        type: 'POST',
                        data: {
                            password: function(res) {
                                return $('#password').val();
                            }
                        }
                    }       
                }
            },
            messages: {
                userLoginId: {
                    remote: '이미 사용 중인 아이디입니다.',
                    minlength: '아이디는 최소 2자 이상이어야 합니다.'
                },
                password : {
                    remote: '비밀번호는 숫자, 영문, 특수문자를 포함하여 최소 8자리 이상이어야 합니다.',
                    minlength: '비밀번호는 최소 8자 이상이어야 합니다.'
                }
            },
            submitHandler: function() {
            	console.log("submit Handler Called!");
                var userBodyData = {
                    userLoginId: $('#login_id').val(),
                    userNm: $('#user_name').val(),
                    password: $('#password').val(),
                    sex: $('#sex').val(),
                    dateOfBirth: $('#birth').val(),
                    hintAnswer: $('#hint_comment').val()
                };

                var hintValue = $('#hint_question').val();
                var hintValues = hintValue.split(',');

                var hintParamData = {
                    pswdHintSn: hintValues[0],
                    hintCn: hintValues[1]
                };

                var formData = {
                    body: userBodyData,
                    param: hintParamData
                };

                $.ajax({
                    type: 'POST',
                    url: "/inco/user/insertUser.json",
                    contentType: 'application/json',
                    data: JSON.stringify(formData),
                    success: function(response) {
                        console.log(response);
                        alert('회원가입이 완료되었습니다.');
                        location.href = '/inco/login';
                    },
                    error: function(xhr, status, error) {
                        console.log(xhr.responseText);
                        alert('회원가입에 실패했습니다. 다시 시도해주세요.');
                    }
                });
            }
        });  
        
        
        $("#submitBtn").on("click", function (e) {
        	e.preventDefault();
        	$('#registerForm').submit(); 
        });
        
    });
</script>


<h2>회원가입 폼</h2>
<form id="registerForm" method="POST">
	<label for="login_id">로그인 ID:</label> <input type="text" id="login_id"name="userLoginId" required> 
	<br><br> 
	<label for="user_name">사용자 이름:</label> <input type="text" id="user_name" name="userNm" required> 
	<br><br> 
	<label for="password">비밀번호:</label> <input type="password" id="password" name="password" required>
	<br><br> 
	<label for="sex">성별:</label> <select id="sex" name="sex">
		<option value="M">남성</option>
		<option value="F">여성</option>
	</select> 
	<br><br> 
	<label for="birth">생년월일:</label> <input type="date" id="birth" name="dateOfBirth" required>
	<br><br> 
	<select id="hint_question" name="hintQuestion">
    <c:forEach var="hintQuestion" items="${hintQuestions}" >
        <option value="${hintQuestion.pswdHintSn} , ${hintQuestion.hintCn}">${hintQuestion.hintCn}</option>
    </c:forEach>
	</select>
	<br><br> 
	<label for="hint_comment">비밀번호 힌트 답변 : </label> <input type="text" id="hint_comment" name="hintAnswer"> 
	<br><br> <!-- <input type="submit" value="가입하기"> -->
 	<input type="button" id="submitBtn" value="가입하기">
 </form>
