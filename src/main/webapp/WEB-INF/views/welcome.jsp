<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="common/include.jsp" />
<title>환영합니다.</title>
<script>
	$(document).ready(function() {
		selectBoardList(1);
		/*
		$(".dropdown-item").on("click", function() {
			var selectedPageUnit = $(this).data("pageunit");
			$("#pageUnit").val(selectedPageUnit);	
	        selectBoardList(1);
		});
		*/
	});
	
	function selectBoardList(pageNo) {
		var param = {
			pageNo : pageNo,
			pageSize : 10
		}
		$.ajax({
			type : "POST",
			url : "/board/selectBoardList",
			contentType : "application/json;",
			data : JSON.stringify(param),
			success : function(res) {
				console.log(res.content);
				var list = res.content;
				var html = '';
				
				$("#boardTableBody").empty();
				
				var viewLength = list.length;
		
				if(list.length == 0) {
					html += '<tr>';
					html += '	<td colspan="5">게시글이 없습니다.</td>';
					html += '</tr>';
				}
				
				for (var i = 0; i < list.length; i++) {
					if(list[i].usingOption == 'Y'){		
					var createdAt = new Date(list[i].createdAt); 
					var date = createdAt.getFullYear() + '년 ' + (createdAt.getMonth()+1) + '월 ' + createdAt.getDate() + '일';
					html += '<tr>';
					if(list[i].priorityPostingOption == 'Y'){
				  	  html += 	'<td>' + '<span class = "badge bg-info text-dark">' + '공지' + '</span>' + '</td>'
					}else{
					  html += 	'<td>' + (i + 1) + '</td>'
					}
				    html += 	'<td><a href="/board/read/' + list[i].boardSn + '">' + list[i].boardTitle + '</a></td>'
				    html += 	'<td>' + list[i].user.userNm + '</td>'
				    html += 	'<td>' + date + '</td>'
				    html += '<td>'
				    html += 		'<button class="btn btn-outline-primary" onclick="fnDeleteBoard('+list[i].boardSn+');">삭제</button>'				   
				    html += 		'<button class="btn btn-outline-primary" onclick="fnUpdateBoard('+list[i].boardSn+');">수정</button>' // 닫는 태그 변경
				    html += '</td>'
				    html += '</tr>'
					}else{
						viewLength--;
					}
				}
				
				$("#boardTableBody").html(html);
				
				param.totalPages = res.totalPages;
				param.totalElements = res.totalElements;
				param.onclickNm = 'selectBoardList';
 				createPagination(param);
			},
			error : function(res) {
				alert("페이지 네이션 로드 실패" + res);
			}
		})
	}
	
	function fnDeleteBoard(boardSn) {
		$.ajax({
			type: "DELETE",
		    url: "/board/delete/" + boardSn,
			success : function(res) {
				selectBoardList(1);
				alert("게시글 삭제 완료");
			},
			error : function(e) {
				alert("게시글 삭제 실패");
			}
		})
	}

	// 수정 페이지를 띄워야함!
	function fnUpdateBoard(boardSn) {
    $.ajax({
        type: "GET",
        url: "/board/updateBoard/" + boardSn,
        success: function (res) {
            location.href = "/board/updateBoard/" + boardSn;
        },
        error: function (e) {
            // 오류 처리
        }
    });
}

	
	/*
	Prev : 이전 페이지 (이전 그룹)
	Next : 다음 페이지 (다음 그룹)
	First : 첫 페이지로
	End : 끝 페이지로
	*/
	function createPagination(pages) {
	var pageNo = pages.pageNo; // 현재 페이지 번호
	var pageUnit = pages.pageSize; // 한 화면에 보여줄 기사 개수
	var totalPages = pages.totalPages; // 전체 페이지
	var totalElements = pages.totalElements; 
	
	console.log('createPagination is called')
	console.log(pages);
	
	console.log('받아온 페이지 값 : ' + totalPages , totalElements)
	
	
	$("#pagination").empty(); // 기존 페이지네이션 초기화
	
	
	var currentGroup = Math.ceil(pageNo / pageUnit);
	var firstPageNo = (currentGroup - 1) * pageUnit + 1; // 그룹 내 첫 번째 번호
	var lastPageNo = Math.min(currentGroup * pageUnit, totalPages); // 그룹 내 마지막 번호
	
	var endPageNo = Math.ceil(totalElements / pageUnit);
	
	var onclickNm = (pages && pages.onclickNm) || 'fnSelectList';
	onclickNm = 'javascript:' + onclickNm;
	
	var totalPageNo = totalElements / totalPages; // 총 페이지 수
	
	console.log("총 페이지 넘버 : " + totalPageNo);
	
	var before = Math.max(firstPageNo - 1, 1); // 이전 페이지
	var after = Math.min(lastPageNo + 1, totalElements + 1); // 다음 페이지
	
	var html = '<ul class="pagination">'; // html 생성
	if(pageNo > pageUnit) { // 첫 페이지
		html += "<li class='page-item'><a class='page-link' href='" + onclickNm + "("+1+")'>First</a></li>";
	}else {
		html += "<li class='page-item disabled'><a class='page-link' href='javascript:void(0)'>First</a></li>";
	}
	
	if(pageNo > 1) { // Prev 버튼
		html += "<li class='page-item'><a class='page-link' href='" + onclickNm + "("+before+")'>Prev</a></li>";
	}else {
		html += "<li class='page-item disabled'><a class='page-link' href='javascript:void(0)'>Prev</a></li>";
	}
	
	for (var i = firstPageNo; i <= lastPageNo; i++) { 
	    html += "<li class='page-itemxltmxhfl " + (pageNo == i ? ' active' : '') + "'><a class='page-link' href='" + onclickNm + "(" + i + ")'>" + i + "</a></li>";
	}
	
	if (pageNo < totalPages) { // 다음 버튼
	    	html += "<li class='page-item'><a class='page-link' href='" + onclickNm + "(" + after + ")'>Next</a></li>";
	}else{    	
			html += "<li class='page-item disabled'><a class='page-link' href='javascript:void(0)'>Next</a></li>";
	}
	
	if(currentGroup == Math.ceil(totalPages/pageUnit)) { // 마지막 페이지
		html += "<li class='page-item disabled'><a class='page-link' href='javascript:void(0)'>End</a></li>";
	}else {
		html += "<li class='page-item'><a class='page-link' href='" + onclickNm + "(" + endPageNo + ")'>End</a></li>";
	}
	
	html += '</ul>';  
	$("#pagination").append(html);
	}
</script>
<body>
	<div class="container text-center mt-5">
		<h3>${sessionScope.loginUser.userNm}님</h3>
		<div class="row">
			<div class="col-sm-12 text-lg-start text-right">
				<input type="button" class="btn btn-primary" style="float:right;" id="createBoardBtn" value="글쓰기" onclick="location.href='/board/createBoard'">
			</div>
		</div>
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">등록일</th>
						<th scope="col">버튼</th>
					</tr>
				</thead>
				<tbody id="boardTableBody">
				</tbody>

			</table>

		</div>
	</div>

	<div class="container text-center mb-3 d-flex justify-content-center">
		<nav aria-label="Page navigation">
			<ul class="pagination" id="pagination"></ul>
		</nav>
	</div>
	<!-- 
		<div>
			<input type="button" class="btn btn-secondary" id="modifyBtn"
				value="회원 정보 수정"
				onclick="window.location.href='/user/modifyUserPW.do';">
		</div>
		 
	    <input type="button" id="deleteBtn" class="btn btn-danger" value="탈퇴하기"  onclick="window.location.href">
	    -->


</body>
