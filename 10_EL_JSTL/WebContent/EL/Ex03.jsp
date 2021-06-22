<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>여러가지 관계(비교) 연산자</h2>
	<h3>
		\${10 == 10 } >>> ${10 == 10 }<br>
		\${10 eq 10 } >>> ${10 eq 10 }<br>
		<br>
		\${"hello" == "hello" } >>> ${"hello" == "hello" }<br>
		\${"hello" eq "hello" } >>> ${"hello" eq "hello" }<br>
		<br>
		\${20 != 10 } >>> ${20 != 10 }<br>
		<%-- \${20 ne 10 } >>> ${20 != 10 }<br> --%>
		<br>
		\${10 < 10 } >>> ${10 < 10 }<br>
		\${10 lt 10 } >>> ${10 lt 10 }<br>
		<br>
	</h3>

</body>
</html>