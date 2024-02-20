<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common/include.jsp" />
<title>환영합니다.</title>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<body>
	<div class="container text-center mt-5">
		<h3>${sessionScope.loginUser.userNm}님</h3>
		<div class="float-right mb-3">
			<input type="button" class="btn btn-primary" id="createBoardBtn"
				value="글쓰기" onclick="location.href='/board/createBoard';">
		</div>
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">등록일</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${empty boardList}">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${boardList}" var="board">
							<tr>
								<td>${board.boardSn}</td>
								<td><a href="/board/read/${board.boardSn}">${board.boardTitle}</a></td>
								<td>${board.user.userNm}</td>
								<fmt:parseDate var="parsedDate" value="${board.createdAt}" pattern="yyyy-MM-dd" />
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDate}" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</div>
	<div class="container text-center mt-5">
		<div>
			<input type="button" class="btn btn-secondary" id="modifyBtn"
				value="회원 정보 수정"
				onclick="window.location.href='/user/modifyUserPW.do';">
		</div>
		<!--  
	    <input type="button" id="deleteBtn" class="btn btn-danger" value="탈퇴하기"  onclick="window.location.href">
	    -->
	</div>
</body>
