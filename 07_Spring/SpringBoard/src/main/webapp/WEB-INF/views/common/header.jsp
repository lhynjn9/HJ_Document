<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 31의 {pageContext.request.contextPath } = context 경로 : 너무 김, 저장해서 사용하는게 좋을 듯 => 해결-->
<c:set var = "root" value="${pageContext.request.contextPath}"></c:set>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


	<c:choose>
		<c:when test ="{!empty username}">
			${username }님 안녕하세요, 
			<a href="${root }/user/logout">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="${root }/user/login">로그인</a>
			<a href="${root }/user/join">회원가입</a>
		</c:otherwise>
	</c:choose>