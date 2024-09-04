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
	<form action="write" method="post">
		<input name="rnum">
		<input name="idx">
		<input name="writer">
		<input name="title">
		<input name="content">
		<input name="readCount">
		<input name="createAt">
		<input name="ip">
		<input name="commentCount">
		<button type="submit">저장</button>
	</form>
</body>
</html>