<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function upd(f) {
		
		if(!confirm("수정할거?")){
			return;
		}
		
		f.action="update.do";
		f.submit();
	}
</script>
</head>
<body>
	<form>
	<input name="idx" value="${vo.idx}" type="hidden">
		<table border="1" style="border-collapse: collapse;">
			<caption>::: 글쓰기 :::</caption>
			<tr>
				<th>작성자</th>
				<td><input name="name" value="${vo.name}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" style="resize: none;">${vo.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="수정하기"
					onclick="upd(this.form)"> <input type="button" value="뒤로가기"
					onclick="history.back();"></td>
			</tr>
		</table>
	</form>
</body>
</html>