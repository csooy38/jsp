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

	<c:set var="dto" value="${Del }"></c:set>
	<div align="center">
		<hr width="50%" color="tomato">
		<h3>${dto.getBoard_writer() }님 게시글 삭제 폼</h3>
		<hr width="50%" color="tomato">
		<br> <br>
	
		<form method="post" action="<%=request.getContextPath() %>/board_deleteOk.do?no=${dto.getBoard_no() }">
		<%-- <input type="hidden" value="${dto.getBoard_no() }" name="no"> --%>
		<input type="hidden" value="${dto.getBoard_pwd() }" name="db_pwd">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>글 제목</th>
					<td>${dto.getBoard_title() }</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="확인">&nbsp;&nbsp;&nbsp;
						<input type="button" value="취소" onclick="location.href='<%=request.getContextPath() %>/board_cont.do?no=${dto.getBoard_no() }'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>