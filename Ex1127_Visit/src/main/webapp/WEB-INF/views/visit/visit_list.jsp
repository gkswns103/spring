<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/visit/resources/css/visit.css">

<script src="/visit/resources/js/httpRequest.js"></script>

<script>
	
			function upd(f) {
				if( f.pwd.value == "" ){
					alert("비밀번호 입력");
					return;
				}
				
				let url = "update_password_chk.do";
				let param = "pwd=" + f.pwd.value + "&c_pwd=" + f.c_pwd.value;
				
				sendRequest(url,param,updFn,"post");
				
			}

			
			function updFn() {
				if(xhr.readyState == 4 && xhr.status == 200){
					let data = xhr.responseText;
					
					if( data == "no_pwd" ){
						alert("비밀번호 틀림");
						return;
					}
					
					let f = document.getElementById("myf");
					
					f.action = "update_form.do";
					f.method = "post";
					f.submit();
				}
			}
			
			function del(f) {
				if(f.pwd.value == ""){
					alert("비밀번호를 입력하세요");
					return;
				}

				if(!confirm("진짜삭제?")){
					return;
				}
				
				let url = "delete.do";
				let param = "idx=" + f.idx.value + "&page=" + f.page.value + "&pwd=" + f.pwd.value + "&c_pwd" + f.c_pwd.value;
				
				sendRequest(url,param,resultFn,"POST");
			}
			
			function resultFn() {
				if( xhr.readyState == 4 && xhr.status == 200 ){
					let data = xhr.responseText;
					let json = (new Function('return ' + data))();
					
					if( json[0].res == "네" ){
						alert("삭제 성공");
					}
					else if(json[0].res == "아니요"){
						alert("삭제 실패");
					}
					else{
						alert("비밀번호 다름");
						return;
					}
					
					location.href="list.do";
				}
			}
		</script>
</head>
<body>
	<div id="main_box">
		<h1>::: 방명록 리스트 :::</h1>
		<div align="center">
			<input type="button" value="글쓰기"
				onclick="location.href='insert_form.do'">
		</div>

		<c:forEach var="vo" items="${list}">
			<div class="visit_box">
				<div class="type_content">
					${vo.content}<br>
					<img src="/visit/resources/upload/${vo.filename}" width="300" height="200">
				</div>
				<div class="type_name">${vo.name}(${vo.ip})</div>
				<div class="type_regdate">${vo.regdate}</div>

				<form id="myf">
					<input name="idx" type="hidden" value="${vo.idx}"> <input
						name="pwd" type="hidden" value="${vo.pwd}"> <input
						name="page" type="hidden" value="${param.page}"> 비밀번호 : <input
						name="c_pwd" type="password"> <input type="button"
						value="수정" onclick="upd(this.form);"> <input type="button"
						value="삭제" onclick="del(this.form);">
				</form>
			</div>
		</c:forEach>
		<div align="center">${pageMenu}</div>
	</div>
</body>
</html>