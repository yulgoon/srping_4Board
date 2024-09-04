<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 전체 목록</title>
</head>
<body>
	<h2>게시글 전체 목록</h2>
	<form action="search" method="get">
		<input name="writer" value="${dto.writer }">
		<input name="title" value="${dto.title }">
		<input name="content" value="${dto.content }">
		<input name="pageNo" value="${pageNo }">
		<button type="submit">검색</button>
	</form>
</body>
</html>