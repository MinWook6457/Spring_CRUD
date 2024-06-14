<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="common/include.jsp"/>

<%
    String title = "test";
    System.out.println("안녕");
%>

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

    html,
    body {
        height: 100%;
    }

    .form-signin {
        max-width: 330px;
        padding: 1rem;
    }

    .form-signin .form-floating:focus-within {
        z-index: 2;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>

<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<!-- Custom styles for this template -->
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="form-signin w-100 m-auto">
    <form id="loginForm" method="post" action="/home/login">
        <img class="mb" src="/resources/image/brand/img.png" alt="" width="298" height="150">
        <div class="form-floating">
            <input type="text" class="form-control" id="user_lgn_id" name="userLoginId" required autofocus placeholder="아이디">
            <label for="floatingInput">로그인 아이디</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="user_pw" name="userPassword" required placeholder="비밀번호">
            <label for="floatingPassword">비밀번호</label>
        </div>

        <button class="btn btn-dark w-100 py-2 mb-5" type="submit" id="loginBtn">로그인</button>
        <button class="btn btn-primary w-100 py-2 mb-1" type="button" id="registerBtn" onclick="window.location.href='/user/insertUser.do';">회원가입</button>
        <button class="btn btn-outline-primary w-100 py-2 mb-1" type="button" id="modifyPwBtn" onclick="window.location.href='/user/modifyUserPW';">비밀번호 변경</button>
        <button class="btn btn-outline-primary w-100 py-2 mb-1" type="button" id="statisticsBtn" onclick="window.location.href='/system/statistics';">통계 보러가기</button
        <button class="btn btn-outline-secondary w-100 py-2 mt-3" type="button" id="guestLoginBtn">비회원 로그인</button>
        <p class="mt-5 mb-3 text-body-secondary">&copy; Min-wook Kim</p>
    </form>
</main>

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

        $("#guestLoginBtn").click(function() {
            $.ajax({
                type: 'POST',
                url: "/home/guestLogin",
                success: function(response) {
                    console.log(response.data)
                    alert('비회원 로그인 성공');
                    location.href = '/welcome';
                },
                error: function(xhr, status, error) {
                    console.log(xhr.responseText);
                    alert("비회원 로그인 실패");
                }
            });
        });
    });


</script>
