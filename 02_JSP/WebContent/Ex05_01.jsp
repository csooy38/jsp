<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	// 한글 깨짐 처리
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	// trim() : 맨 앞과 맨 뒤의 공백이 있으면 공백을 제거하는 메서드.
	String userId = request.getParameter("id").trim();
	String userPwd = request.getParameter("pwd").trim();
	String userName = request.getParameter("name").trim();
	String userGender = request.getParameter("gender").trim();
	String userAddr = request.getParameter("addr").trim();
	String userPhone = request.getParameter("phone").trim();
	String userEmail = request.getParameter("email").trim();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="50%" color="tomato">
			<h3>가입 회원 정보</h3>
		<hr width="50%" color="tomato">
		<table border="1" cellspacing="0" width="300">
			<tr>
				<th>아이디</th>
				<td><%=userId %>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><%=userPwd %>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=userName %>
			</tr>
			<tr>
				<th>성별</th>
				<td><%=userGender %>
			</tr>
			<tr>
				<th>주소</th>
				<td><%=userAddr %>
			</tr>
			<tr>
				<th>연락처</th>
				<td><%=userPhone %>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%=userEmail %>
			</tr>
		</table>
	</div>

</body>
</html>