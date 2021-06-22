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
	
	<c:set var="dto" value="${Cont }"></c:set>
	<c:set var="c_list" value="${c_list }"></c:set>
	
	<div align="center">
		<hr width="50%" color="green">
			<h3>${dto.getBoard_writer() }님 게시글 상세 내용</h3>
		<hr width="50%" color="green">
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
					<td><textarea rows="8" cols="30" readonly>${dto.getBoard_cont() }</textarea></td>
				</tr>
				
				<tr>
					<th>조 회</th>
					<td>${dto.getBoard_hit() }</td>
				</tr>
				
				<tr>
					<th>작성일</th>
					<td>${dto.getBoard_regdate() }</td>
				</tr>
			</c:if>
			<c:if test="${empty dto }">
				<tr>
					<td>게시글이 존재하지 않습니다.</td>
				</tr>
			</c:if>									
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정" onclick="location.href='<%=request.getContextPath() %>/board_update.do?no=${dto.getBoard_no() }&page=${page }'">
					<input type="button" value="삭제" onclick="location.href='<%=request.getContextPath() %>/board_delete.do?no=${dto.getBoard_no() }&page=${page }'">
					<input type="button" value="글목록" onclick="location.href='<%=request.getContextPath() %>/board_list.do?page=${page }'">
				</td>
			</tr>
		</table>
		<br>
		
		<form method="post" action="<%=request.getContextPath() %>/board_comment.do">
			<input type="hidden" name="no" value="${dto.getBoard_no() }">
			<input type="hidden" name="page" value="${page }">
			<table>
				<tr>
					<td colspan="2" align="left">
						<b>댓글</b>
					</td>
				</tr>
				<tr>
					<td><input type="text" name="c_writer" placeholder="닉네임" size="8"></td>
					<td><input type="text" name="c_content" placeholder="댓글 내용" size="30"></td>
					<td><input type="password" name="c_pwd" placeholder="비밀번호" size="8"></td>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		
			<hr width="40%" color="lightgray">
		
			
			<table>
			<c:if test="${!empty c_list }">
				<c:forEach items="${c_list }" var="c_dto">
					<tr>
						<th>${c_dto.getComment_writer() }</th>
						<th>&nbsp;|&nbsp;</th>
						<td width="250">${c_dto.getComment_cont() }</td>
						<td align="right">${c_dto.getComment_sysdate() }</td>
						<td><input type="button" value="x" onclick="location.href='<%=request.getContextPath()%>/comment_delete.do?no=${dto.getBoard_no() }&page=${page }&cnum=${c_dto.getComment_no() }'"></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty c_list }">
				<tr>
					<td colspan="5" align="center">
						<h4>작성된 댓글이 없습니다.</h4>
					</td>
				</tr>
			</c:if>
			</table>
			
		
	</div>

</body>
</html>