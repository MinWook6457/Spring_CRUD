<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/include.jsp" />

<script>
    $(document).ready(function(){
        $("#createComment").submit(function(event) {
            event.preventDefault();

            var boardSn = "${board.boardSn}";

            console.log(boardSn);


            var comment = {
                boardSn: boardSn,
                content: $('#comment').val()
            };

            console.log(comment);

            $.ajax({
                method: 'POST',
                url: "http://localhost:8080/comment/createComment",
                contentType: 'application/json',
                data: JSON.stringify(comment),
                success: function(response) {
                    alert('댓글 작성 완료');
                    location.href = '/board/read/${board.boardSn}';
                },
                error: function(xhr, status, error) {
                    alert('댓글 작성 중 오류가 발생하였습니다.');
                    console.log("Error details:", xhr, status, error);
                }
            });
        });
    });


</script>

<body>
<div class="container mt-5">
    <article class="posting">
        <div class="form-group">
            <label for="title">제목</label>
            <textarea class="form-control" id="title" rows="2" readonly>${board.boardTitle}</textarea>
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="5" readonly>${board.boardContent}</textarea>
        </div>
        <div style="text-align: right">
            <label for="title">등록일 : </label>
            <fmt:parseDate var="parsedDate" value="${board.createdAt}" pattern="yyyy-MM-dd" />
            <fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDate}" />
        </div>
        <div class="card-body">
            <div class="mb-3">
                <label for="image" class="form-label">이미지</label>
                <c:forEach var="file" items="${board.files}">
                    <c:choose>
                        <c:when test="${file.fileName.endsWith('.png') or file.fileName.endsWith('.jpg') or file.fileName.endsWith('.jpeg')}">
                            <div class="mb-3">
                                <input type="image" id="boardImage" class="form-control" src="${file.filePath}" readonly>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
        <div class="uploadDiv">
            <label for="${board.files}">첨부파일</label>
            <ul>
                <c:forEach var="file" items="${board.files}">
                    <li>
                        <c:choose>
                            <c:when test="${file.fileName.endsWith('.png') or file.fileName.endsWith('.jpg') or file.fileName.endsWith('.jpeg')}">
                                <img src="${file.filePath}" alt="${file.fileName}" style="max-width: 100%; height: auto;" />
                            </c:when>
                            <c:otherwise>
                                <a href="/download/${file.filePath}" download="${file.fileName}">
                                        ${file.fileName}
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </article>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <a class="btn btn-outline-primary" href="/welcome" role="button">뒤로가기</a>
    </div>

    <div class="mt5">
        <h5>댓글</h5>
        <ul>
            <c:forEach var="comment" items="${comments}">
                <li>
                        ${comment.user.userNm} : ${comment.commentContent} -
                    <fmt:parseDate var="parsedDate" value="${comment.createdAt}" pattern="yyyy-MM-dd" />
                    <fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDate}" />
                </li>
            </c:forEach>
        </ul>
    </div>

    <form id="createComment" method="POST">
        <div class="form-group">
            <label for="comment">댓글</label>
            <textarea class="form-control" id="comment" name="commentContent" rows="1" required></textarea>
        </div>
        <div class="col-sm-12 d-flex justify-content-between">
            <button class="btn btn-outline-primary w-3 text-bg-dark mb-3" type="submit" id="createCommentBtn">작성하기</button>
        </div>
    </form>

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
