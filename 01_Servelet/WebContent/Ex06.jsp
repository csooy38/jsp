<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- [문제] 성적처리 폼 만들기 --%>
	<div align="center">
		<hr width=50% color="red">
		<h2>성적 확인</h2>
		<hr width=50% color="red">

		<form method="post" action="score">
			<table border="1" cellspacing="0">
				<tr>
					<th>이 름</th>
					<td><input type="text" name="name"></td>
				</tr>

				<tr>
					<th>국어 점수</th>
					<td><input type="text" name="kor"></td>
				</tr>

				<tr>
					<th>영어 점수</th>
					<td><input type="text" name="eng"></td>
				</tr>

				<tr>
					<th>수학 점수</th>
					<td><input type="text" name="mat"></td>
				</tr>

				<tr>
					<th>자바 점수</th>
					<td><input type="text" name="java"></td>
				</tr>

				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="성적처리">
						&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
					</td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>