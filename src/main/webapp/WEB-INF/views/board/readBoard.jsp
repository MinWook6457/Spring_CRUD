<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/include.jsp" />
<body>
<div class="container mt-5">
    <article class="posting">
        <div class="form-group">
            <label for="title">제목</label>
            <textarea class="form-control" id="title" rows="5" readonly>${board.boardTitle}</textarea>
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="5" readonly>${board.boardContent}</textarea>
        </div>
        <label for="title">등록일</label>
        <div class="card-footer text-muted">
            <fmt:parseDate var="parsedDate" value="${board.createdAt}" pattern="yyyy-MM-dd" />
            <fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDate}" />
        </div>
    </article>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <a class="btn btn-outline-primary" href="/welcome" role="button">뒤로가기</a>
    </div>
</div>
<script>
    // Function to resize textareas to fit their content
    function resizeTextarea(id) {
        const textarea = document.getElementById(id);
        textarea.style.height = 'auto'; // Reset the height
        textarea.style.height = textarea.scrollHeight + 'px'; // Set it to the scroll height
    }

    // Automatically resize the textareas on page load
    window.onload = function() {
        resizeTextarea('title');
        resizeTextarea('content');
    };
</script>
</body>
