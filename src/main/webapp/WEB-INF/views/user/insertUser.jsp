<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/include.jsp" />


<title>회원가입 폼</title>

<script>
$(document).ready(function() {
	$(function() {
	    $("#birth").datepicker({
	    	format: 'yyyy-mm-dd'
	    });
	});
	
    $('#registerForm').validate({
        rules: {
            userLoginId: {
                required: true,
                minlength: 2,
                remote: {
                    url: '/user/checkDuplicateId',
                    type: 'POST',
                    data: {
                        userLoginId: function() {
                            return $('#login_id').val();
                        }
                    }
                }
            },
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
            }
        },
        messages: {
            userLoginId: {
                remote: '이미 사용 중인 아이디입니다.',
                minlength: '아이디는 최소 2자 이상이어야 합니다.'
            },
            password: {
                remote: '비밀번호는 숫자, 영문, 특수문자를 포함하여 최소 8자리 이상이어야 합니다.',
                minlength: '비밀번호는 최소 8자 이상이어야 합니다.'
            }
        },
        submitHandler: function(form){
            console.log("submit!");
            console.log(form);
            form.submit(); // 폼 제출
        },
        
        invalidHandler: function(event, validator) {
        	   console.log("이거 안 됨 ㅋㅋ");
        }
    });
    $('#submitBtn').click(function(event) {
        event.preventDefault(); 
        if ($('#registerForm').valid()) {
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
                url: "/user/insertUser.json",
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function(response) {
                    console.log(response);
                    alert('회원가입이 완료되었습니다.');
                    location.href = '/home';
                },
                error: function(xhr, status, error) {
                    console.log(xhr.responseText);
                    alert('회원가입에 실패했습니다. 다시 시도해주세요.');
                }
            });
        }
    });
});

(function () {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()

</script>

<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .btn-bd-primary {
        --bd-violet-bg: #712cf9;
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #6528e0;
        --bs-btn-hover-border-color: #6528e0;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #5a23c8;
        --bs-btn-active-border-color: #5a23c8;
      }

      .bd-mode-toggle {
        z-index: 1500;
      }

      .bd-mode-toggle .dropdown-menu .active .bi {
        display: block !important;
      }
      
      .storm {
       	display: inline-block;
        text-align: center;
      }
      
      .form-label {
        margin-bottom: 0.5rem; /* 텍스트 입력란 위 간격 조절 */
  	  }
</style>

<body class="bg-body-tertiary">
<div class="container">
  <main>
    <div class="py-5 text-center">
      <img class="d-block mx-auto mb-4" src="/resources/image/brand/bootstrap-logo.svg" alt="" width="72" height="57">
      <h2>회원가입</h2>
    </div>
    <div class="row g-6">
      <div class="col-md-12 col-lg-12">
        <h4 class="mb-3">모든 정보를 입력해주세요!</h4>
        <form class="row g-3 needs-validation" novalidate="" id="registerForm"  method="POST">
           	<div class="row g-2">
            	<div class="col-sm-4">
             		 <label for="login_id" class="form-label">Input Login ID</label>
             		 <input type="text" class="form-control" id="login_id" placeholder="아이디" required="" minlength="4">
             		 <div class="invalid-feedback">
    				 아이디는 2자 이상!
    				 </div>
            	</div>

            	<div class="col-sm-4">
             		 <label for="user_name" class="form-label">UserName</label>
               		 <input type="text" class="form-control" id="user_name">
           		</div>

            	<div class="col-sm-4">
              		 <label for="password" class="form-label">Password</label>
              		 <input type="password" class="form-control" id="password" placeholder="비밀번호" pattern="^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$">
           		 	 
           		 	 <div class="invalid-feedback">
    				  비밀번호는 특수문자,영어,숫자 포함 8글자 이상입니다.
    				  </div>
           		 </div>
            
           		 <div class="col-sm-2 mt-3">
              	     <label for="sex" class="form-label">회원 성별 설정</label>
            		 	<select id="sex" class ="form-select" name="sex">
               				<option value="">성별</option>
               					<option>M</option>
               					<option>F</option>
              			</select>
           		 </div>

          		  <div class="col-sm-4 mt-3">
              	  	  <label for="hint_question" class="form-label">비밀번호 힌트 설정</label>
              	     	<select id="hint_question" class ="form-select" name="hintQuestion">
              	     		<option value="">힌트 질문 선택</option>
  							<c:forEach var="hintQuestion" items="${hintQuestions}" >
    	  						  <option value="${hintQuestion.pswdHintSn} , ${hintQuestion.hintCn}">${hintQuestion.hintCn}</option>
    						</c:forEach>
						</select>
				  </div>
           
            	  <div class="col-sm-3 mt-3">
              		   <label for="hint_comment" class="form-label">질문 답변</label>
              		   <input type="text" class="form-control" id="hint_comment">
           		  </div>  
           		  
           		  <div class="col-sm-3 mt-3">
   				 		<label for="birth" class="form-label">생년월일</label>
						<input type="text" id="birth" class="form-control" value="2000-01-01" />
				 </div>
				 <div class = "row-6 mt-3">
						<div class="col text-center">
							<button class="btn btn-outline-primary" id=submitBtn type="button">가입하기</button>	
						</div>
					</div>	
				 </div>
				</form>
      			</div>
			</div>
 		</main>
 	</div>
 </body>
