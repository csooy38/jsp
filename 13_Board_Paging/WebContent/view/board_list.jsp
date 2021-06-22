<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="page" value="${page }"></c:set>

	<div align="center">
		<hr width="50%" color="skyblue">
			<h3>BOARD 테이블 게시물 전체 리스트</h3>
		<hr width="50%" color="skyblue">
		<br><br>
		
		<table border="1" cellspacing="0" width="600" style="text-align:center;">
			<tr>
				<th>글번호</th> <th>제 목</th> <th>작성자</th> <th>조 회</th> <th>작성일자</th>
			</tr>
			
			<c:set var="list" value="${List }"></c:set>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td> <a href="<%=request.getContextPath() %>/board_cont.do?no=${dto.getBoard_no() }&page=${page }">
						${dto.getBoard_title() } 
						<c:if test="${dto.getBoard_c_count() ne 0 }">
						(${dto.getBoard_c_count() })</c:if></a></td>
						<td>${dto.getBoard_writer() }</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_regdate().substring(0,10) }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="5" align="center">
						<h3>검색된 게시글이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="5" align="right">
					<input type="button" value="글쓰기" onclick="location.href='<%=request.getContextPath() %>/board_write.do'">
				</td>
			</tr>
		</table>
		<br>
		
		<c:if test="${page > block }">
			<a href="board_list.do?page=1">[처음]</a>
			<a href="board_list.do?page=${startBlock - 1 }">◀</a>
		</c:if>
		
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="board_list.do?page=${i }">[${i }]</a></b>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="board_list.do?page=${i }">[${i }]</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endBlock < allPage }">
			<a href="board_list.do?page=${endBlock + 1 }">▶</a>
			<a href="board_list.do?page=${allPage }">[마지막]</a>
		</c:if>
		
		<br><br>
		<form method="post" action="<%=request.getContextPath() %>/board_search.do">
			<select name="find_field">
				<option value="all">전체</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
				<option value="writer">작성자</option>
			</select>
			<input type="text" name="find_text" size="15">
			<input type="submit" value="검색">
		</form>
	</div>

</body>
</html>