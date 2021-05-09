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
		<h2>학생 정보 입력 화면</h2>
		<form method="post" action="student">
			<table border="1" cellspacing="0">
				<tr>
					<th>학 번</th>
					<td> <input type="text" name="hakbun"> </td>
				</tr>
				
				<tr>
					<th>이 름</th>
					<td> <input type="text" name="name"> </td>
				</tr>
				
				<tr>
					<th>전공과목</th>
					<td>
						<input type="checkbox" name="major" value="Java">Java &nbsp;&nbsp;
						<input type="checkbox" name="major" value="C언어">C언어 &nbsp;&nbsp;
						<input type="checkbox" name="major" value="JSP">JSP &nbsp;&nbsp;
						<input type="checkbox" name="major" value="Spring">Spring &nbsp;&nbsp;
						<input type="checkbox" name="major" value="PHP">PHP &nbsp;&nbsp; 
					</td>
				</tr>
				
				<tr>
					<th>연락처</th>
					<td> <input type="text" name="phone"> </td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="등록">
							&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>