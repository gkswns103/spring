<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>고객번호</th>
			<th>고객이름</th>
			<th>고객주소</th>
			<th>고객주민번호</th>
			<th>담당직원번호</th>
		</tr>
		<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.gobun}</td>
				<td>${vo.goname}</td>
				<td>${vo.goaddr}</td>
				<td>${vo.gojumin}</td>
				<td>${vo.godam}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>