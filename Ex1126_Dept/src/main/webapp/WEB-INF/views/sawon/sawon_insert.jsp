<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	function ins(f) {
		f.submit();
	}
</script>

</head>
<body>
		<form action="insert_sawon.do" method="get">
		<table>
			<tr>
				<th>이름</th>
				<td><input name="saname"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<select name="sagen">
						<option value="">성별을 선택하세요</option>
						<option value="남자">남자</option>
						<option value="여자">여자</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>부서</th>
				<td>
					<select name="deptno">
						<option value="">부서를 선택하세요</option>
						<option value="10">총무부</option>
						<option value="20">영업부</option>
						<option value="30">전산실</option>
						<option value="40">관리부</option>
						<option value="50">경리부</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>직책</th>
				<td><input name="sajob"></td>
			</tr>
			<tr>
				<th>상사번호</th>
				<td><input name="samgr"></td>
			</tr>
			<tr>
				<th>급여</th>
				<td><input name="sapay"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="추가하기" onclick="ins(this.form)">
					<input type="button" value="뒤로가기" onclick="history.back();">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>