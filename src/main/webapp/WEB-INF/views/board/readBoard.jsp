<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/include.jsp" />
<title>${board.boardTitle}</title>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

<body>
    <div class="container mt-5">
        <div class="card">
            <h5 class="card-header">${board.boardTitle}</h5>
            <div class="card-body">
                <p class="card-text">${board.boardContent}</p>
            </div>
            <div class="card-footer text-muted">
                <fmt:parseDate var="parsedDate" value="${board.createdAt}" pattern="yyyy-MM-dd" />
				<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDate}" />
            </div>
        </div>
    </div>
</body>
