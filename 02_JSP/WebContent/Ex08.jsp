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
		<hr width="50%" color="red">
			<h2>메인 페이지</h2>
		<hr width="50%" color="red">
		
		<h3><%=request.getParameter("id") %>님 환영합니다.</h3>
		<h4>
			비밀번호 : <%=request.getParameter("pwd") %> <br>
			이  름 : <%=request.getAttribute("name") %> <br>
			주  소 : <%=request.getAttribute("addr") %> <br>
		</h4>
	</div>

</body>
</html>