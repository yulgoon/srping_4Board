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
			<!-- 글 내용 중 enter는 화면에 줄이 바뀌어야 하지만 문자열의 enter는 \r\n이고 browser의 줄 바꿈은 <br>
				 method로 변경하는 것은 나중에 하고 여기서는 <pre> tag를 사용하겠습니다.
			 -->
			<td>
				<pre><c:out value="${dto.content }"/></pre>
				<!-- 아래처럼 쓰면 html, script 형식의 텍스트를 script 처리합니다.
					 따라서, 악성 코드 등이 심기기 좋은 코드이므로 절대로 지양해야 합니다. -->
				<%-- <pre>${dto.content }</pre> --%>
			</td>
			<td><c:out value="${dto.writer }"/></td>
			<td><c:out value="${dto.readCount }"/></td>
			<td><c:out value="${dto.createdAt }"/></td>
		</tr>
	</table>
	<div>
		<div class="btn"><a href="modify?idx=${dto.idx }&pageNo=${pageNo }">수정</a></div>
		<div class="btn"><a href="#">삭제</a></div>
		<div class="btn"><a href="list?pageNo=${pageNo }">목록</a></div>
	</div>
	<form action="remove" method="post">
		<input type="hidden" name="idx" value="${dto.idx }">
		<input type="hidden" name="pageNo" value="${pageNo }">
		<!-- submit 버튼은 만들지 않고 위의 삭제 버튼이 클릭 되면 자바 스크립트를 실행해서 submit() 실행 -->
	</form>
</body>
</html>