<%@page import="com.sist.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 한글 깨짐 방지 설정
	request.setCharacterEncoding("UTF-8");

	/* String memid = request.getParameter("memid");
	String memname = request.getParameter("memname");
	String pwd = request.getParameter("pwd");
	int age = Integer.parseInt(request.getParameter("age"));
	int mileage = Integer.parseInt(request.getParameter("mileage"));
	String job = request.getParameter("job");
	String addr = request.getParameter("addr");
	
	MemberDTO dto = new MemberDTO();
	dto.setMemid(memid);
	dto.setMemname(memname);
	dto.setPwd(pwd);
	dto.setAge(age);
	dto.setMileage(mileage);
	dto.setJob(job);
	dto.setAddr(addr); */
%>

<jsp:useBean id="dto" class="com.sist.model.MemberDTO" scope="request" />

<%--<jsp:setProperty property="memid" name="dto"/>
<jsp:setProperty property="memname" name="dto"/>
<jsp:setProperty property="pwd" name="dto"/>
<jsp:setProperty property="age" name="dto"/>
<jsp:setProperty property="mileage" name="dto"/>
<jsp:setProperty property="job" name="dto"/>
<jsp:setProperty property="addr" name="dto"/> --%>


<%-- 전송된 매개변수의 이름과  빈 객체(DTO)의 멤버변수 이름을 비교하여
빈 객체의 이름과 매개변수의 이름이 같은 경우에는 빈에 값을 자동으로 설정하라는 의미--%>

<jsp:setProperty property="*" name="dto"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="red">	
			<h3><%=dto.getMemname() %>님의 회원 정보</h3>
		<hr width="50%" color="red">
		
		<table border="1" cellspacing="0" width="350">
			<tr>
				<th>회원 아이디</th>
				<td><%=dto.getMemid() %>
			</tr>
			<tr>
				<th>회원 이름</th>
				<td><%=dto.getMemname() %>
			</tr>
			<tr>
				<th>회원 비밀번호</th>
				<td><%=dto.getPwd() %>
			</tr>
			<tr>
				<th>회원 나이</th>
				<td><%=dto.getAge() %>
			</tr>
			<tr>
				<th>회원 마일리지</th>
				<td><%=dto.getMileage() %>
			</tr>
			<tr>
				<th>회원 직업</th>
				<td><%=dto.getJob() %>
			</tr>
			<tr>
				<th>회원 주소</th>
				<td><%=dto.getAddr() %>
			</tr>
		
		</table>
	</div>

</body>
</html>