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
		out.println("----- getAttribute() 메서드 사용 결과 -----<br>");
	
		String userId = (String) session.getAttribute("id");
		String userPwd = (String) session.getAttribute("pwd");

		out.println("session id >>> " + userId + "<br>");
		out.println("session pwd >>> " + userPwd + "<br>");
		
		out.println("----- session 유효 시간 사용 결과 -----<br>");
		// getMaxInactiveInterval() : 세션의 유효시간을 얻어 올 때 사용하는 메서드.
		int time = session.getMaxInactiveInterval(); 	
		out.println("session 유효 시간 >>> " + time + "<br>");
		
		out.println("----- removeAttribute() 사용 결과 -----<br>");
		session.removeAttribute("pwd");
		String pwd = (String)session.getAttribute("pwd");
		out.println("session pwd >>> " + pwd + "<br>");
	%>

</body>
</html>