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

	<c:set var="list" value="${List }"></c:set>
	<c:set var="find_field" value="${find_field }"></c:set>
	<c:set var="find_text" value="${find_text }"></c:set>
	
	<div align="center">
		<hr width="50%" color="yellowgreen">
			<h3>검색결과(${totalRecord }) | 
			
			<c:if test="${find_field == 'all' }">
				<c:set var="field" value="전체"></c:set>
			</c:if>
			<c:if test="${find_field == 'title' }">
				<c:set var="field" value="제목"></c:set>
			</c:if>
			<c:if test="${find_field == 'content' }">
				<c:set var="field" value="내용"></c:set>
			</c:if>
			<c:if test="${find_field == 'title_content' }">
				<c:set var="field" value="제목+내용"></c:set>
			</c:if>
			<c:if test="${find_field == 'writer' }">
				<c:set var="field" value="작성자"></c:set>
			</c:if>
			
			${field } : <span style="color:tomato">${find_text }</span></h3>
		<hr width="50%" color="yellowgreen">
		<br><br>
		
		<table border="1" cellspacing="0" width="600" style="text-align:center;">
			<tr>
				<th>글번호</th> <th>제 목</th> <th>작성자</th> <th>조 회</th> <th>작성일자</th>
			</tr>
			
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td> <a href="<%=request.getContextPath() %>/board_cont.do?no=${dto.getBoard_no() }&page=${page }">
						${dto.getBoard_title() } <c:if test="${dto.getBoard_c_count() ne 0 }">
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
						&nbsp;&nbsp;&nbsp;
					<input type="button" value="글목록" onclick="location.href='<%=request.getContextPath() %>/board_list.do'">
				</td>
			</tr>
		</table>
		<br>
		
		<c:if test="${page > block }">
			<a href="board_search.do?page=1&find_field=${find_field }&find_text=${find_text }">[처음]</a>
			<a href="board_search.do?page=${startBlock - 1 }&find_field=${find_field }&find_text=${find_text }">◀</a>
		</c:if>
		
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="board_search.do?page=${i }&find_field=${find_field }&find_text=${find_text }">[${i }]</a></b>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="board_search.do?page=${i }&find_field=${find_field }&find_text=${find_text }">[${i }]</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endBlock < allPage }">
			<a href="board_search.do?page=${endBlock + 1 }&find_field=${find_field }&find_text=${find_text }">▶</a>
			<a href="board_search.do?page=${allPage }&find_field=${find_field }&find_text=${find_text }">[마지막]</a>
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