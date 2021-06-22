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

	<c:set var="dto" value="${cont }"></c:set>
	
	<div align="center">
		<hr width="50%" color="lightgray">
			<h3>${dto.getBoard_no() }번 게시글 상세 내역</h3>
		<hr width="50%" color="lightgray">
		<br><br>
		
		<table border="1" cellspacing="0" width="400">
			<c:if test="${!empty dto }">
				<tr>
					<th>작성자</th>
					<td>${dto.getBoard_writer() }</td>
				</tr>
				
				<tr>
					<th>제 목</th>
					<td>${dto.getBoard_title() }</td>
				</tr>
				
				<tr>
					<th>내 용</th>
					<td><textarea rows="7" cols="30" readonly>${dto.getBoard_cont() }</textarea></td>
				</tr>
				
				<tr>
					<th>조회수</th>
					<td>${dto.getBoard_hit() }</td>
				</tr>
				
				<tr>
					<th>작성일</th>
					<td>${dto.getBoard_date() }</td>
				</tr>
			</c:if>
			
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>게시글이 존재하지 않습니다.</h3>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onclick="location.href='<%=request.getContextPath() %>/bbs_update.do?no=${dto.getBoard_no()}&page=${page }'">
					<input type="button" value="삭제하기" onclick="location.href='<%=request.getContextPath() %>/bbs_delete.do?no=${dto.getBoard_no()}&page=${page }'">
					<input type="button" value="답글달기" onclick="location.href='<%=request.getContextPath() %>/bbs_reply.do?no=${dto.getBoard_no()}&page=${page }'">
					<input type="button" value="전체목록" onclick="location.href='<%=request.getContextPath() %>/bbs_list.do?page=${page }'">
				</td>
			</tr>
		</table>
	</div>

</body>
</html>