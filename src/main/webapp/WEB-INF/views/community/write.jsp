<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성(등록)</title>
</head>
<body>
	<h2>글 쓰기(새 글 등록)</h2>
	<hr>
	<form action="write" method="post" name="frmWrite" onsubmit="return isValid()">
		<!-- return이 참이면 submit, 거짓이면 submit 안 합니다. -->
		<input type="hidden" name="ip" value="<%= request.getRemoteAddr() %>">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="30" cols="80" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<!-- submit은 server로 입력값들을 전송합니다.
					 javascript에도 form 객체의 요소에 submit 함수를 갖고 있습니다.
				 -->
					<button type="submit">저장</button>
					<button type="reset">다시쓰기</button>
					<button type="button" onclick="location.href='list?pageNo=${pageNo }'">목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>