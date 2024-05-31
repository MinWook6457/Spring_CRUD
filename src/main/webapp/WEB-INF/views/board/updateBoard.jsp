<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../common/include.jsp" />

<script>
	$(document).ready(function() {
		$("#modifyBoard").submit(function(event) {
			event.preventDefault();

			var updatingData = {
				boardSn : '${board.boardSn}',
				boardTitle : $('#title').val(),
				boardContent : $('#content').val(),
				priorityPostingOption : $('#priorityPostingOption').val(),
				usingOption : $('#usingOption').val(),
				userLoginId : '${sessionScope.loginUser.userLoginId}'
			};

			$.ajax({
				type : 'POST',
				url : "/board/updateBoard",
				contentType : 'application/json',
				data : JSON.stringify(updatingData),
				success : function(response) {
					alert('글 수정 완료');
					location.href = '/welcome';
				}
			});
		});
	});
</script>

<body>
	<div class="container">
		<h1 class="mt-5">글 수정 페이지</h1>
		<form id="modifyBoard" method="POST">
		<input type="hidden" id="boardSn" value="${board.boardSn}"> <!-- hidden input 추가 -->
			<div class="form-group">
				<label for="title">제목</label> <input type="text"
					class="form-control" id="title" value="${board.boardTitle}"
					required>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" id="content" rows="5" required>${board.boardContent}</textarea>
			</div>
			<div class="form-group">
				<label for="priorityPostingOption">우선 게시 여부</label>
				<c:choose>
					<c:when test="${fn:contains(board.priorityPostingOption, 'Y')}">
						<select class="form-control" id="priorityPostingOption">
							<option value="Y" selected>예</option>
							<option value="N">아니요</option>
						</select>
					</c:when>
					<c:otherwise>
						<select class="form-control" id="priorityPostingOption">
							<option value="Y">예</option>
							<option value="N" selected>아니요</option>
						</select>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<label for="usingOption">사용 여부</label>
				<c:choose>
					<c:when test="${fn:contains(board.usingOption, 'Y')}"> 
						<select class="form-control" id="usingOption">
							<option value="Y" selected>사용</option>
							<option value="N">사용하지 않음</option>
						</select>
					</c:when>
					<c:otherwise>
						<select class="form-control" id="usingOption">
							<option value="Y">사용</option>
							<option value="N" selected>사용하지 않음</option>
						</select>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">수정</button>
				<a href="/home" class="btn btn-secondary">취소</a>
			</div>
		</form>
	</div>
</body>
