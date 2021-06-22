<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int practice_no = Integer.parseInt(request.getParameter("no"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="pink">
			<h3>PRACTICE DELETE CONFIRM</h3>
		<hr width="50%" color="pink">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath() %>/delete.do">
		<input type="hidden" value="<%=practice_no %>" name="no">
		<table border="1" cellspacing="0">
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="pwd"></td>
			</tr>
		</table>
		<br><br>
		<input type="submit" value="확인"> &nbsp;&nbsp;&nbsp;
		<input type="button" value="취소" onclick="location.href='<%=request.getContextPath() %>/select.do'">
		</form>
	</div>

</body>
</html>