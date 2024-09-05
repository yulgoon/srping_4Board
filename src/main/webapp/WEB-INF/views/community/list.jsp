<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 전체 목록</title>
<link rel="stylesheet" href="../resources/css/list.css">
</head>
<body>
	<h2>커뮤니티 게시글 전체 목록</h2>
	<hr>
	<table>
		<tr>
			<td>번호</td>
			<td>글제목</td>
			<td>작성자</td>
			<td>조회수</td>
			<td>날짜</td>
		</tr>
		<!-- controller에서 페이지 글목록을 list 이름으로 저장해야 합니다. -->
		<c:forEach items="${list }" var="dto">
			<tr>
				<td><c:out value="${dto.rnum }"/></td>
				<td><a href="read?idx=${dto.idx }&pageNo=${pageDto.pageNo }"><c:out value="${dto.title }"/></a></td>
				<td><c:out value="${dto.writer }"/></td>
				<td><c:out value="${dto.readCount }"/></td>
				<td><c:out value="${dto.createdAt }"/></td>
			</tr>
		</c:forEach>
	</table>
	<p>현재 페이지 정보: <c:out value="${pageDto }"/></p>
	<!-- 글쓰기를 완료 시에는 1페이지로 가면 되지만 글쓰기 화면에서 중단하고 원래 글목록 페이지로 이동할 때를 위하여 pageNo를 전달합니다. -->
	<div class="btn"><a href="write?pageNo=${pageDto.pageNo }">글쓰기</a></div>
</body>
</html>