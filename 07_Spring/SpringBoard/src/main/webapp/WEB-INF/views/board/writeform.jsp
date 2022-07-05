<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
<%@incldue file = "/WEB-INF/views/common/header.jsp" %>
</head>
<body>
	<div class="container">
		<h2>자유게시판</h2>
		<!-- 버튼이 눌리면 write로 모든 정보를 가지고 가게 됨 -->
		<!-- mutipart : 파일 업로드 가능 -->
		<form action="${root }/board/write" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="act" value="write" />
			<div class="mb-3">
				<label for="title" class="form-label">글제목</label>
				<input type="text" class="form-control" id="title" name="title">
			</div>
			<div class="mb-3">
				<label for="writer" class="form-label">글쓴이</label>
				<input type="text" clas="form-control" id="writer" name="writer">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">글내용</label>
				<textarea class="form-control" id="content" name="content">
			</div>
			<div class="mb-3">
				<input class="form-control" type="file" name="upload_file">
			</div>
			<button class="btn btn-primary">등록</button>
		</form>
	</div>
</body>
</html>