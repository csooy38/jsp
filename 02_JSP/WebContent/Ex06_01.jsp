<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("id").trim();
	String userPwd = request.getParameter("pwd").trim();
	
	// 원래는 DB 회원 관련 테이블에서 입력한 id와 pwd가 맞는지
	// 확인하여 회원이면 메인 페이지로 이동
	String dbId = "hong";
	String dbPwd = "1234";
	
	if(userId.equals(dbId)) {	// 입력한 아이디와 DB 테이블 상의 아이디가 같은  경우
		if(userPwd.equals(dbPwd)) {	// 입력한 비밀번호와 DB 테이블 상의 비밀번호가 같은  경우
			// 아이디와 비밀번호가 모두 일치하여 회원인 경우
			// 메인 페이지로 이동 ==> 페이지 이동
			request.setAttribute("name", "홍길동");
			request.setAttribute("addr", "서울시 마포구");
			
			RequestDispatcher rd = request.getRequestDispatcher("Ex07.jsp");
			rd.forward(request, response);	// request 데이터와 함께 실제로 페이지 이동				
		}
	}
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