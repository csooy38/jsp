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
	<c:set var="dto" value="${edit }"></c:set>
	
	<div align="center">
		<hr width="50%" color="yellowgreen">
			<h3>${dto.getBoard_writer() }님 게시글 수정 폼</h3>
		<hr width="50%" color="yellowgreen">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath() %>/board_updateOk.do">
		<input type="hidden" name="page" value="${page }">
		<input type="hidden" name="no" value="${dto.getBoard_no() }">
		<input type="hidden" name="db_pwd" value="${dto.getBoard_pwd() }">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>작성자</th>
					<td>${dto.getBoard_writer() }</td>
				</tr>
				
				<tr>	
					<th>제 목</th>
					<td><input type="text" name="title" value="${dto.getBoard_title() }"></td>
				</tr>
				
				<tr>
					<th>내 용</th>
					<td><textarea rows="7" cols="30" name="content">${dto.getBoard_cont() }</textarea></td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정하기"> &nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성"> &nbsp;&nbsp;&nbsp;
						<input type="button" value="취소" onclick="location.href='<%=request.getContextPath() %>/board_cont.do?no=${dto.getBoard_no() }&page=${page }'">
					</td>
				</tr>
			</table>
		</form>	
	</div>

</body>
</html>