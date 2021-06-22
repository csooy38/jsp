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

	<c:set var="dto" value="${del }"></c:set>
	<div align="center">
		<hr width="50%" color="tomato">
		<h3>${dto.getMemname() } 회원 삭제</h3>
		<hr width="50%" color="tomato">
		<br>
		<br>

		<form method="post"
			action="<%=request.getContextPath()%>/deleteOk.do">
		<input type="hidden" value="${dto.getNum() }" name="mem_num">
		<table border="1" cellspacing="0" width="350">
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="mem_pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인">
						&nbsp;&nbsp;&nbsp;
					<input type="button" value="취소" onclick="location.href='content.do?num=${dto.getNum()}'">
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>