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

	<div align="center">
		<hr width="50%" color="lightgray">
			<h3>JSP_Board 테이블 게시판 전체 리스트</h3>
		<hr width="50%" color="lightgray">
		<br><br>
		
		<table border="1" cellspacing="0" width="600">
			<tr>
				<th>번 호</th> <th>제 목</th><th>작성자</th> <th>조 회</th> <th>작성일자</th>
				<th>Group</th> <th>step</th> <th>Indent</th>
			</tr>
			
			<c:set var="list" value="${List }"></c:set>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td> 
							<c:forEach begin="1" end="${dto.getBoard_indent() }">
								&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
						
						<a href="<%=request.getContextPath() %>/bbs_cont.do?no=${dto.getBoard_no() }&page=${page }">
						
						<c:forEach begin="1" end="${dto.getBoard_indent() }">
								re)
							</c:forEach>
						${dto.getBoard_title() }</a></td>
						
						
						<td>${dto.getBoard_writer() }</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_date().substring(0,10) }</td>
						
						<td>${dto.getBoard_group() }</td>
						<td>${dto.getBoard_step() }</td>
						<td>${dto.getBoard_indent() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="8" align="center">
						<h3>검색된 게시글이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="8" align="right">
					<input type="button" value="글쓰기" onclick="location.href='<%=request.getContextPath() %>/bbs_write.do'">
				</td>
			</tr>
		</table>
		<br>
		
		<c:if test="${page > block }">
			<a href="bbs_list.do?page=1">[처음]</a>
			<a href="bbs_list.do?page=${startBlock - 1 }">◀</a>
		</c:if>
		
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="bbs_list.do?page=${i }">[${i }]</a></b>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="bbs_list.do?page=${i }">[${i }]</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endBlock < allPage }">
			<a href="bbs_list.do?page=${endBlock + 1 }">▶</a>
			<a href="bbs_list.do?page=${allPage }">[마지막]</a>
		</c:if>
		
		<br><br>
		<form method="post" action="<%=request.getContextPath() %>/bbs_search.do">
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