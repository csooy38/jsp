<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="orange">
		<h3>BOARD 게시판</h3>
		<hr width="50%" color="orange">
		<br> <br>

		<table border="1" cellspacing="0" width="600"
			style="text-align: center;">
			<tr>
				<th>번 호</th>
				<th>제 목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:set var="list" value="${List }"></c:set>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td><a href="<%=request.getContextPath() %>/board_content.do?no=${dto.getBoard_no()}">
							${dto.getBoard_title() }</a></td>
						<td>${dto.getBoard_writer() }</td>
						<td>${dto.getBoard_regdate() }</td>
						<td>${dto.getBoard_hit() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<td colspan="5" align="center">
					<h3>검색된 글 목록이 없습니다.</h3>
				</td>
			</c:if>

			<tr>
				<td colspan="5" align="right"><input type="button" value="글쓰기"
					onclick="location.href='<%=request.getContextPath()%>/board_write.do'"></td>
			</tr>
		</table>
		<br><br>
		
		<form method="post" action="<%=request.getContextPath() %>/board_search.do">
			<select name="search">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
				<option value="writer">작성자</option>
			</select>
			<input type="text" size="15" name="searchData">
			<input type="submit" value="검색">
		</form>
	</div>

</body>
</html>