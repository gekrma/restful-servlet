<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>franchisesListView.jsp</title>
<style>
#franchises {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 80%;
  margin: auto;
}

#franchises td, #franchises th {
  border: 1px solid #ddd;
  text-align: center;
  padding: 8px;
}

#franchises tr:nth-child(even){background-color: #f2f2f2;}

#franchises tr:hover {background-color: #ddd;}

#franchises th {
  padding-top: 12px;
  padding-bottom: 12px;
  background-color: #4CAF50;
  color: white;
}

p {
  width: 90%;
  text-align: right;
}

a {
  text-decoration: none;
  font-weight: bold;
  color: black;
}

button {
  position: absolute;
  right: 10%;
}

h2 {
  text-align: center;
}
</style>
</head>
<body>
	<h2>프랜차이즈 현황 정보</h3>
	<p><금액단위 : 천원, 가맹점 현황정보는 직전사업년도 말 기준></p>
	<table id="franchises">
		<tr>
			<th>No</th>
			<th>브랜드</th>
			<th>상호</th>
			<th>가맹점수<a href="franchises.do?order=franchises_num">&dtrif;</a></th>
			<th>평균매출액<a href="franchises.do?order=avg_sales">&dtrif;</a></th>
			<th>면적당 평균매출액<a href="franchises.do?order=area_avg_sales">&dtrif;</a></th>
		</tr>
		<c:forEach items="${requestScope.list}" var="data" varStatus="LoopStatus" >
			<tr>
				<td>${LoopStatus.count}</td>
				<td>
					<form action="./franchises.do" method="post">
						<input type="hidden" name="command" value="franchisesView">
						<input type="hidden" name="data" value="${data}">
	    				<a href="javascript:;" onclick="parentNode.submit();">${data.brand}</a>
					</form>
				</td>
				<td>${data.company}</td>
				<td><fmt:formatNumber type="number" value="${data.franchisesNum}"/></td>
				<td><fmt:formatNumber type="number" value="${data.avgSales}"/></td>
				<td><fmt:formatNumber type="number" value="${data.areaAvgSales}"/></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<button onclick="location.href='main.jsp'">메인 화면으로 돌아가기</button>
	<br>
	<br>
</body>
</html>