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
			<h3>UPLOAD 테이블 자료실 게시물 삭제 폼 페이지</h3>
		<hr width="50%" color="tomato">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath() %>/upload_delete_ok.do">
			<input type="hidden" name="upload_no" value="${number }">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="upload_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="삭제하기">
						<input type="button" value="취소" onclick="location.href='<%=request.getContextPath() %>/upload_cont.do?no=${number }'">
					</td>
				</tr>
			</table>
		</form>		
	</div>

</body>
</html>