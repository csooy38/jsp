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
		<hr width="50%" color="tomato">
			<h3>BOARD 글 작성</h3>
		<hr width="50%" color="tomato">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath() %>/board_writeOk.do">
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>제 목</th>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer"></td>
				</tr>
				<tr>
					<th>내 용</th>
					<td><textarea cols="22" rows="8" name="context"></textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글쓰기">
						<input type="reset" value="다시작성">
						<input type="button" value="취소" onclick="location.href='<%=request.getContextPath() %>/board_list.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>