<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스트푸드 프랜차이즈 현황</title>
<style>
.btn-group .button {
  background-color: #4CAF50; /* Green */
  border: 1px solid green;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  cursor: pointer;
  width: 150px;
  display: block;
  position: relative;
  left: 10%;
}

.btn-group .button:not(:last-child) {
  border-bottom: none; /* Prevent double borders */
}

.btn-group .button:hover {
  background-color: #3e8e41;
}
</style>
</head>
<body>
	<h1>패스트푸드 프랜차이즈 현황</h1>
	<div class="btn-group">
	  <button class="button" onClick="location.href='franchises.do'">프랜차이즈 현황 정보</button>
	  <button class="button" onClick="location.href='view/franchisesInsertView.jsp'">프랜차이즈 추가</button>
	</div>
</body>
</html>