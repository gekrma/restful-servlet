<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
	${requestScope.error}
	<br>
	<a href="main.jsp">메인 화면으로 돌아가기</a>
</body>
</html>