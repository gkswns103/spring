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
	<table border="1" >
		<caption>::: ${list[0].deptno}부서의 정보 :::</caption>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>성별</th>
			<th>부서번호</th>
			<th>직종</th>
			<th>입사날짜</th>
			<th>상사번호</th>
			<th>급여</th>
			<th>비고</th>
		</tr>
		<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.sabun}</td>
				<td>${vo.saname}</td>
				<td>${vo.sagen}</td>
				<td>${vo.deptno}</td>
				<td>${vo.sajob}</td>
				<td>${vo.sahire}</td>
				<td>${vo.samgr}</td>
				<td>${vo.sapay}</td>
				<td colspan="2">
					<input value="수정" type="button" onclick="location.href='sawon_update_form.do?sabun=${vo.sabun}';">
					<input value="삭제" type="button" onclick="location.href='sawon_delete_form.do?sabun=${vo.sabun}&deptno=${vo.deptno}';">
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="추가하기" onclick="location.href='sawon_insert_form.do'">
	<input type="button" value="뒤로가기" onclick="history.back()">
</body>
</html>