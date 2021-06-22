<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>표현식으로 scope 내용 출력</h2>
	<h3>
		page >>> <%=pageContext.getAttribute("K") %><br>
		request >>> <%=request.getAttribute("r") %><br>
		session >>> <%=session.getAttribute("s") %><br>
		application >>> <%=application.getAttribute("a") %><br>
	</h3>
	<br><br><hr><br>
	<h2>표현언어(EL)로 scope 내용 출력</h2>
	<h3>
		page >>> ${pageScope.K } == ${K }<br>
		request >>> ${requestScope.r } == ${r }<br>
		session >>> ${sessionScope.s } == ${s }<br>
		application >>> ${applicationScope.a } == ${a }<br>
	</h3>
	
	<script type="text/javascript">
		location.href="Ex07.jsp";
	</script>

</body>
</html>