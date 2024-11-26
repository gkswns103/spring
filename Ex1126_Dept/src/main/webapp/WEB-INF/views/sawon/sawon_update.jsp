<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	function upd(f) {
		if(!confirm("수정할겨?")){
			return;
		}
		f.submit();
	}
</script>

</head>
<body>
	<form action="update_sawon.do" method="get">
		<input type="hidden" name="sabun" value="${vo.sabun}">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input name="saname" value="${vo.saname}"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input name="sagen" value="${vo.sagen}"></td>
			</tr>
			<tr>
				<th>부서번호</th>
				<td><input name="deptno" value="${vo.deptno}"></td>
			</tr>
			<tr>
				<th>직종</th>
				<td><input name="sajob" value="${vo.sajob}"></td>
			</tr>
			<tr>
				<th>상사번호</th>
				<td><input name="samgr" value="${vo.samgr}"></td>
			</tr>
			<tr>
				<th>급여</th>
				<td><input name="sapay" value="${vo.sapay}"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="수정하기" onclick="upd(this.form)">
					<input type="button" value="뒤로가기" onclick="history.back();">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>