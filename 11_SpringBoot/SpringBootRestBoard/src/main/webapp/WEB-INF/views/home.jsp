<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- context 경로/BoardController의 RequestMapping/BoardController의 GetMapping -->
<!-- {pageContext.request.contextPath } = context 경로 : 너무 김, 저장해서 사용하는게 좋을 듯 -->
<!-- 33에서 해결 -->
	<!-- <a href="${pageContext.request.contextPath }/board/list">게시글 보기</a> -->
	<!-- BoardController로 이동 -->
	<a href="${root }/board/list">게시글 보기</a>
</body>
</html>