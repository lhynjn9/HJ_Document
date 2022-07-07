<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<%@include file="/WEB-INF/views/common/header.jsp" %>
</head>
<body>
	<div class="containter">
		<h2>자유게시판</h2>
		<hr>
		<div>
			<table class="table text-center">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>
				<!-- 게시물 조회 시, list라는 키 값으로 넣어주어야 함 -->
				<c:forEach items="${list }" var="board">
					<tr>
						<td>${board.id }</td>
						<td>
							<!-- 제목이 눌리때 detail로 가면 id로 구별 -->
							<a href='${root }/board/detail&id=${board.id }'> ${board.title }
						</a></td>
						<td>${board.writer }</td>
						<td>${board.viewCnt }</td>
						<td>${board.regDate }</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<form action="${root }/board/list">
					<select name="mode">
						<option value="1">제목
						<option value="2">내용
						<option value="3">제목+내용
					</select>
					<input type="text" name="keyword">
					<input type="submit" name="검색">
				</form>
			</div>
			<div class="d-flex justify-content-end">
				<a class="btn btn-outline-danger" href="${root }/board/writeForm">등록</a>
			</div>
		</div>
	</div>
	
	<script>
		// board/api/board 데이터를 가져옴
		const xhr = new XMLHttpRequest()
	</script>
</body>
</html>