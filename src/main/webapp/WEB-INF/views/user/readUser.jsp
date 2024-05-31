<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/include.jsp" />

<body>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-lg-6">
        <div class="card">
          <div class="card-header bg-primary text-white">
            내정보 조회
          </div>
          <div class="card-body">
            <div class="mb-3">
              <label for="username" class="form-label">사용자명</label>
              <input type="text" id="username" class="form-control" value="${sessionScope.loginUser.userNm}" readonly>
            </div>
            <div class="mb-3">
              <label for="username" class="form-label">아이디</label>
              <input type="text" id="loginId" class="form-control" value="${sessionScope.loginUser.userLoginId}" readonly>
            </div>
            <div class="mb-3">
              <label for="username" class="form-label">비밀번호</label>
              <input type="text" id="password" class="form-control" value="${sessionScope.loginUser.password}" readonly>
            </div>
            <div class="mb-3">
              <label for="username" class="form-label">성별</label>
              <input type="text" id="sex" class="form-control" value="${sessionScope.loginUser.sex}" readonly>
            </div>
            <div class="mb-3">
              <label for="birth" class="form-label">생년월일</label>
              <input type="text" id="birth" class="form-control" readonly>
            </div>

          </div>
          <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a class="btn btn-outline-primary" href="/welcome" role="button">뒤로가기</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>


<script>
  function setBirthDate() {
    // 2000-01-09 09:00:00
    var datetimeString = "${sessionScope.loginUser.dateOfBirth}";

    var datePart = datetimeString.split(" ")[0];

    document.getElementById("birth").value = datePart;
  }

  window.onload = function() {
    setBirthDate();
  };
</script>