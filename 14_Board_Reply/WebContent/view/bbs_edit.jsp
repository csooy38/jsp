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

	<c:set var="dto" value="${edit }"></c:set>
	
	<div align="center">
		<hr width="50%" color="lightgray">
			<h3>${dto.getBoard_no() }번 게시글 수정 폼</h3>
		<hr width="50%" color="lightgray">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath() %>/bbs_update_ok.do">
			<input type="hidden" name="no" value="${dto.getBoard_no() }">
			<input type="hidden" name="db_pwd" value="${dto.getBoard_pwd() }">
			<input type="hidden" name="page" value="${page }">
			<table border="1" cellspacing="0" width="400">
				<c:if test="${!empty dto }">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" value="${dto.getBoard_writer() }" readonly></td>
				</tr>
				
				<tr>
					<th>제 목</th>
					<td><input type="text" name="title" value="${dto.getBoard_title() }"></td>
				</tr>
				
				<tr>
					<th>내 용</th>
					<td><textarea rows="7" cols="30" name="content">${dto.getBoard_title() }</textarea></td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
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
					<input type="submit" value="확인">
					<input type="reset" value="다시작성">
					<input type="button" value="취소" onclick="location.href='<%=request.getContextPath() %>/bbs_cont.do?no=${dto.getBoard_no() }&page=${page }'">
				</td>
			</tr>
			</table>
		</form>
	</div>

</body>
</html>