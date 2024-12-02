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
		<form action="insert.do" method="post" enctype="multipart/form-data">
			<table border="1" style="border-collapse: collapse;">
				<caption>::: 새 글쓰기 :::</caption>
				<tr>
					<th>작성자</th>
					<td><input name="name"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" style="resize:none;" wrap="on" rows="5" cols="10"></textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input name="pwd" type="password"></td>
				</tr>
				<tr>
					<th>파일</th>
					<td><input type="file" name="photo"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="추가" onclick="ins(this.form)">
						<input type="button" value="뒤로가기" onclick="history.back();">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>