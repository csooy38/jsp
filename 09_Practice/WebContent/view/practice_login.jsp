<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="pink">
			<h3>PRACTICE LOGIN</h3>
		<hr width="50%" color="pink">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath() %>/login.do">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="pr_id"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pr_pwd"></td>
				</tr>
			</table>
			<br><br>
			<input type="submit" value="로그인">&nbsp;&nbsp;&nbsp;
			<input type="button" value="회원가입" onclick="location.href='<%=request.getContextPath() %>/join.do'">
		</form>
	</div>

</body>
</html>