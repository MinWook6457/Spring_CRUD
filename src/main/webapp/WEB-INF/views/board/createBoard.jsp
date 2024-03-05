<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/include.jsp"/>

<script>
$(document).ready(function(){
    $("#writeBoard").submit(function(event){
        event.preventDefault();

        var writingData = {
            boardTitle: $('#title').val(),
            boardContent: $('#content').val(),
            priorityPostingOption: $('#priorityPostingOption').val(),
            usingOption: $('#usingOption').val(),
            isDeletedOption: "N", // Default => N
            userLoginId: '${sessionScope.loginUser.userLoginId}'
        };


        $.ajax({
            type: 'POST',
            url: "/board/writeBoard",
            contentType: 'application/json',
            data: JSON.stringify(writingData),
            success: function(response) {
                alert('글쓰기 완료');
                location.href = '/welcome';
            }
        });
    });
});


</script>

<body>
	<div class="container">
		<h1 class="mt-5"> 글 쓰기 페이지 </h1>
		<form id = "writeBoard" method ="POST">
			 <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
            </div>    
            <div class="form-group">
                <label for="priorityPostingOption">우선 게시 여부</label>
                <select class="form-control" id="priorityPostingOption" name="priorityPostingOption">
                    <option value="Y">예</option>
                    <option value="N" selected>아니요</option>
                </select>
            </div>
            <div class="form-group">
                <label for="usingOption">사용 여부</label>
                <select class="form-control" id="usingOption" name="usingOption">
                    <option value="Y" selected>사용</option>
                    <option value="N">사용하지 않음</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">작성</button>
                <a href="/home" class="btn btn-secondary">취소</a>
            </div>
		</form>
	</div>
</body>