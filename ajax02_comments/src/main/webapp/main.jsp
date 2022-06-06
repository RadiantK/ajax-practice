<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메인 페이지</h1>
	<c:forEach var="b" items="${list}">
		<p>
			${b.title} <a href="${pageContext.request.contextPath}/detail?bnum=${b.bnum}">상세보기</a>
		</p>
	</c:forEach>
</body>
</html>