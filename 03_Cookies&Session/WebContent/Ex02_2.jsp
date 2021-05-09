<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String userId = (String) session.getAttribute("id");
		String userPwd = (String) session.getAttribute("pwd");
		
		out.println("session id >>> " + userId + "<br>");
		out.println("session pwd >>> " + userPwd + "<br>");
	%>

</body>
</html>