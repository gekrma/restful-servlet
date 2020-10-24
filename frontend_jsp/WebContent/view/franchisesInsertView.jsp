<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../style/form.css">
<title>franchisesInsertView.jsp</title>
</head>
<body>
	
<form action="../franchises.do" method="post">
  <input type="hidden" name="command" value="franchisesInsert">
  <input type="hidden" name="_method" value="post">
  <div class="container">
    <h1>프랜차이즈 추가</h1>
    <hr>
    <label>브랜드</label>
    <input type="text" placeholder="브랜드를 입력하세요" name="brand" required>
    <label>상호</label>
    <input type="text" placeholder="상호를 입력하세요" name="company" required>
    <label>가맹점수</label>
    <input type="number" placeholder="가맹점수를 입력하세요" name="franchisesNum" required>
    <label>평균매출액 (천원 단위)</label>
    <input type="number" placeholder="평균매출액을 입력하세요 (천원 단위)" name="avgSales" required>
    <label>면적당 평균매출액 (천원 단위)</label>
    <input type="number" placeholder="면적당 평균매출액을 입력하세요 (천원 단위)" name="areaAvgSales" required>
    <hr>
    <button class="left" type="submit">추가</button>
    <button class="right" onclick="location.href='../main.jsp'">취소</button>
  </div>
</form>

</body>
</html>