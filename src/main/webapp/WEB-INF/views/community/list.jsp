<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 전체 목록</title>
<link rel="stylesheet" href="../resources/css/list.css">
<link rel="stylesheet" href="../resources/css/pageNo.css">
<script type="text/javascript">
/* 이 위치의 script는 DOM이 만들어지고 rendering 되기 전에 실행이 됩니다.  */
/* 특별히 이 위치의 function만 redering 후에 실행이 됩니다. */
	if('${message }'.length != 0) alert('${message }')
</script>
</head>
<body>
	<h2>커뮤니티 게시글 전체 목록</h2>
	<hr>
	<div class="btn"><p>현재 페이지 정보: <c:out value="${pageDto }"/></p></div>
	전체 페이지 수: <c:out value="${pageDto.totalPages }"/>, 전체 글의 개수: <c:out value="${pageDto.totalCount }"/>, 현재 페이지 번호: <c:out value="${pageDto.pageNo }"/>
	
	<div style="width: 70%; margin: 10px auto; padding: 10px;">
	<!-- url은 같고 parameter만 다른 경우 href에 파라미터만 표시할 수 있습니다. -->
		<!-- 이전 리스트로 가기 -->
		<a href="?pageNo=1" class="pagenum">&lt;&lt;</a>
		
		<a class="pagenum" href="?pageNo=${pageDto.startPage-1 }"      
         	style='<c:if test="${pageDto.startPage==1 }">display:none;</c:if>'>&lt;</a>
         	
		<c:forEach var="i" begin="${pageDto.startPage }" end="${pageDto.endPage }">
			<a href="?pageNo=${i }" class="pagenum ino"><c:out value="${i }"/></a>		
		</c:forEach>
		
		<!-- 다음 리스트로 가기 -->
		<a class="pagenum" href="?pageNo=${pageDto.endPage+1 }"
         	style='<c:if test="${pageDto.endPage==pageDto.totalPages }">display:none;</c:if>'>&gt;</a>
         	
		<a class="pagenum" href="?pageNo=${pageDto.totalPages }" >&gt;&gt;</a>	
	</div>
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
	<%-- <p>현재 페이지 정보: <c:out value="${pageDto }"/></p> --%>
	<!-- 글쓰기를 완료 시에는 1페이지로 가면 되지만 글쓰기 화면에서 중단하고 원래 글목록 페이지로 이동할 때를 위하여 pageNo를 전달합니다. -->
	<!-- <div class="btn"><a href="list?pageNo=1">첫 페이지로</a></div> -->
	<div class="btn"><a href="write?pageNo=${pageDto.pageNo }">글쓰기</a></div>
	<script type="text/javascript">
	/* 이 위치의 script는 DOM 이 만들어지고 redering 된 후에 실행이 됩니다. */
		const inos = document.querySelectorAll(".ino")
		console.log(inos)
		inos.forEach(
			(i) => {
				console.log('i =', i.innerHTML.trim())
				if(i.innerHTML=='${pageDto.pageNo }') {
					/* i.style.color='#f00'
					i.style.fontWeight='700' */
					i.classList.add('current')
				}/* else{
					i.style.color='#79747E'
					i.style.backgroundColor='#fff'
					i.classList.add('current')
				} */
			})
	</script>
</body>
</html>