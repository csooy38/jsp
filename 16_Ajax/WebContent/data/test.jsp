<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	String req_param = request.getParameter("param");
	
	System.out.println("요청한 param >>> " + req_param);
	
	// 응답을 해 보자.
	// 실질적으로 응답하는 과정.
	// 실제적으로 데이터가 전송되는 명령어.
	out.println("Ajax 요청에 대한 응답입니다.");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>