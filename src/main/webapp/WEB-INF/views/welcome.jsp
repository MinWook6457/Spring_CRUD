<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<jsp:include page="common/include.jsp"/>
<title>환영합니다.</title>

<script>

</script>


<body>
	<div>
		<h3>${sessionScope.loginUser.userNm} 님</h3>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>TEST</td>
					<td>${sessionScope.loginUser.userNm}</td>
					<td>Time</td>
				</tr>
			</tbody>
		</table>
	</div>
	<input type="button" id="createBoardBtn" value="글쓰기" onclick="window.location.href='/board/create.do';">
	<input type="button" id="modifyBtn" value="회원 정보 수정" onclick="window.location.href='/user/modifyUserPW.do';">
	<!--  
    <input type="button" id="deleteBtn" value="탈퇴하기"  onclick="window.location.href">
    -->
</body>