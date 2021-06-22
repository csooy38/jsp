<%@page import="com.practice.model.PracticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	PracticeDTO cont = (PracticeDTO)request.getAttribute("cont");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="pink">
			<h3>PRACTICE TEXT UPDATE</h3>
		<hr width="50%" color="pink">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath()%>/updateOk.do">
		<input type="hidden" name="no" value="<%=cont.getPractice_no()%>">
		<table border="1" cellspacing="0">
			<tr>
				<th>글 번호</th>
				<td><%=cont.getPractice_no() %></td>
			</tr>
			<tr>
				<th>말머리</th>
				<td><%=cont.getPractice_head() %></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><input type="text" name="title" value="<%=cont.getPractice_title() %>"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="<%=cont.getPractice_writer() %>"></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><%=cont.getPractice_regdate() %></td>
			</tr>
			<tr>
				<th>조 회</th>
				<td><%=cont.getPractice_hit() %></td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><textarea rows="8" cols="30" name="content"><%=cont.getPractice_cont() %></textarea><td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd">
			</td>
		</table>
		<br><br>
		<input type="submit" value="등록">&nbsp;&nbsp;&nbsp;<input type="button" value="취소" onclick="history.back()">
		</form>

</body>
</html>