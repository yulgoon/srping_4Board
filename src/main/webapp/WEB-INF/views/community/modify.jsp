<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성(수정)</title>
</head>
<body>
	<h2>글 쓰기(글 수정)</h2>
	<hr>
	<form action="modify" method="post" name="frmModify" onsubmit="return isValid()">
		<!-- return이 참이면 submit, 거짓이면 submit 안 합니다. -->
		<input type="hidden" name="ip" value="<%= request.getRemoteAddr() %>">
		<input type="hidden" name="pageNo" value="${pageNo }">
		<input type="hidden" name="idx" value="${dto.idx }">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" value="(수정)${dto.title }" disabled>
				<input type="hidden" name="title" value="(수정)${dto.title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${dto.writer }" disabled></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="30" cols="80" name="content"><c:out value="${dto.content }"/></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<!-- submit은 server로 입력값들을 전송합니다.
					 javascript에도 form 객체의 요소에 submit 함수를 갖고 있습니다.
				 -->
					<button type="submit">저장</button>
					<!-- reset: 처음 화면의 value 값으로 복구 시켜 줍니다. -->
					<button type="reset">다시쓰기</button>
					<button type="button" onclick="location.href='list?pageNo=${pageNo }'">목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>