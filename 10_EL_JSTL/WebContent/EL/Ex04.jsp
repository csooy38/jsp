<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>여러 가지 논리 연산자</h2>
	
	<h3>
		\${(10 == 10 ) && (20 == 20) } >>> ${(10 == 10 ) && (20 == 20) }<br>
		\${(10 == 10 ) && (20 == 10) } >>> ${(10 == 10 ) && (20 == 10) }<br>
		\${(10 == 10 ) || (20 == 10) } >>> ${(10 == 10 ) || (20 == 10) }<br>
	</h3>

</body>
</html>