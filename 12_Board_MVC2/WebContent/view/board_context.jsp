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
		<hr width="50%" color="tomato">
			<h3>BOARD 게시글 확인</h3>
		<hr width="50%" color="tomato">
		<br><br>
		
		<table border="1" cellspacing="0" width="300">
		<c:set var="dto" value="${cont }"></c:set>
		<c:if test="${!empty dto }">
			<tr>
				<th>글 번호</th>
				<td>${dto.getBoard_no() }</td>
			</tr>
			
			<tr>
				<th>제 목</th>
				<td>${dto.getBoard_title() }</td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td>${dto.getBoard_writer() }</td>
			</tr>
			
			<tr>
				<th>조회수</th>
				<td>${dto.getBoard_hit() }</td>
			</tr>
			
			<tr>
				<th>내 용</th>
				<td><textarea cols="22" rows="8" readonly>${dto.getBoard_cont() }</textarea></td>
			</tr>
			
			<tr>
				<th>작성일</th>
				<td>${dto.getBoard_regdate() }</td>
			</tr>
		</c:if>
		<c:if test="${empty dto }">
			<tr>
				<td align="center">
					<h3>작성된 글이 없습니다.</h3>
				</td>
			</tr>
		</c:if>
		
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정" onclick="location.href='<%=request.getContextPath() %>/board_update.do?no=${dto.getBoard_no() }'">
						&nbsp;&nbsp;&nbsp;
					<input type="button" value="삭제" onclick="location.href='<%=request.getContextPath() %>/board_delete.do?no=${dto.getBoard_no() }'">
						&nbsp;&nbsp;&nbsp;
					<input type="button" value="글목록" onclick="location.href='<%=request.getContextPath() %>/board_list.do'">
				</td>
			</tr>
		</table>
	</div>

</body>
</html>