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
		<h2>회원 로그인</h2>
		<form action="login">
			<table border="1" cellspacing="0">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" vlaue="로그인">
							&nbsp;&nbsp;&nbsp;
						<input type="reset" vlaue="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>