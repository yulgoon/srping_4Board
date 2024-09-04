<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 보기</title>
<link rel="stylesheet" href="../resources/css/read.css">
</head>
<body>
	<h2>글 상세 보기와 삭제</h2>
	<hr>
	목록으로 돌아가기 <a href="list?pageNo=${pageNo }">Go!</a>
	<table>
		<tr>
			<td>글번호</td>
			<td>글제목</td>
			<td>글내용</td>
			<td>작성자</td>
			<td>조회수</td>
			<td>날짜</td>
		</tr>
		<tr>
			<td><c:out value="${dto.idx }"/></td>
			<td><c:out value="${dto.title }"/></td>
			<td><c:out value="${dto.content }"/></td>
			<td><c:out value="${dto.writer }"/></td>
			<td><c:out value="${dto.readCount }"/></td>
			<td><c:out value="${dto.createdAt }"/></td>
		</tr>
	</table>
	<form action="remove">
		<button type="submit">글삭제</button>
	</form>
</body>
</html>