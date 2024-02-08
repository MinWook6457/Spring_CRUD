<!-- registerForm.jsp -->
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 폼</title>
</head>
<body>
    <h2>회원가입 폼</h2>
    <form action="/inco/registerSuccess" method="get">
        <label for="login_id">로그인 ID:</label>
        <input type="text" id="login_id" name="login_id" required><br><br>
        
        <label for="user_name">사용자 이름:</label>
        <input type="text" id="user_name" name="user_name" required><br><br>
        
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <label for="sex">성별:</label>
        <select id="sex" name="sex">
            <option value="M">남성</option>
            <option value="F">여성</option>
        </select><br><br>
        
        <label for="birth">생년월일:</label>
        <input type="date" id="birth" name="birth" required><br><br>
        
        <label for="hint_comment">비밀번호 힌트:</label>
        <input type="text" id="hint_comment" name="hint_comment"><br><br>
        
        <input type="submit" value="가입하기">
    </form>
</body>
</html>
