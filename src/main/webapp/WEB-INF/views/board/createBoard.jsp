<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/include.jsp"/>

<script>
    $(document).ready(function(){
        $("#writeBoard").submit(function(event){
            event.preventDefault();

            var inputFile = $("input[name='file']")[0]; // 파일 필드 선택
            var files = inputFile.files; // 선택된 파일 가져오기
            console.log(files);

            var formData = new FormData(this); // 입력된 form 데이터 객체 전환 (자동 매핑)

            formData.append('userLoginId', '${sessionScope.loginUser.userLoginId}');


            for(var i =0;i<files.length;i++){
                formData.append("uploadFile", files[i]);
            }

            console.log(formData);

            $.ajax({
                type: 'POST',
                url: "/board/writeBoard",
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    alert('글쓰기 완료');
                    location.href = '/welcome';
                },
                error: function(xhr, status, error) {
                    alert('글쓰기 중 오류가 발생했습니다.');
                }
            });
        });
    });
</script>

<body>
<div class="container">
    <h1 class="mt-5"> 글 쓰기 페이지 </h1>
    <form id="writeBoard" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="boardTitle" name="boardTitle" required>
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="boardContent;" name="boardContent;" rows="5" required></textarea>
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
        <div class="uploadDiv">
            <input type="file" name="file" multiple="multiple">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">작성</button>
            <a href="/home" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>
</body>
