<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./style/form.css">
<title>franchisesView.jsp</title>
</head>
<body>
<c:set var="data" value="${requestScope.data}" />
  <div class="container">
	<form action="franchises.do" method="post">
	  <input type="hidden" name="command" value="franchisesUpdate">
	  <input type="hidden" name="_method" value="put">
	  <h1>프랜차이즈 정보 수정</h1>
	  <hr>
	  <label>브랜드</label>
	  <input type="text" name="brand" value="${data.brand}" readonly required>
	  <label>상호</label>
	  <input type="text" placeholder="상호를 입력하세요" name="company" value="${data.company}" required>
	  <label>가맹점수</label>
	  <input type="number" placeholder="가맹점수를 입력하세요" name="franchisesNum" value="${data.franchisesNum}" required>
	  <label>평균매출액 (천원 단위)</label>
	  <input type="number" placeholder="평균매출액을 입력하세요 (천원 단위)" name="avgSales" value="${data.avgSales}" required>
	  <label>면적당 평균매출액 (천원 단위)</label>
	  <input type="number" placeholder="면적당 평균매출액을 입력하세요 (천원 단위)" name="areaAvgSales" value="${data.areaAvgSales}" required>
	  <hr>
	  <button class="update" type="submit">변경</button>
	</form>
 </div>
 <form action="./franchises.do" method="post">
   <input type="hidden" name="command" value="franchisesDelete">
   <input type="hidden" name="_method" value="delete">
   <input type="hidden" name="brand" value="${data.brand}">
   <button class="delete" type="submit">삭제</button>	
 </form>

</body>
</html>