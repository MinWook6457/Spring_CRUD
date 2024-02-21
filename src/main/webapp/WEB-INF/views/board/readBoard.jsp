<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/include.jsp" />
<title>${board.boardTitle}</title>

<body>
    <div class="container mt-5">
        <article class = "posting">
        <h2 class="display-5 link-body-emphasis mb-1">${board.boardTitle}</h2>
            <label for="title">글내용</label>
            <div class="card-body">
                <p class="card-text">${board.boardContent}</p>
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
</body>
