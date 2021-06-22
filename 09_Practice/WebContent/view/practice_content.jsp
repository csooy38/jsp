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
			<h3>PRACTICE TEXT</h3>
		<hr width="50%" color="pink">
		<br><br>
		
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
				<td><%=cont.getPractice_title() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=cont.getPractice_writer() %></td>
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
				<td><textarea rows="8" cols="30" readonly><%=cont.getPractice_cont() %></textarea><td>
			</tr>
		</table>
		<br><br>
		<input type="button" value="수정" onclick="location.href='<%=request.getContextPath()%>/update.do?no=<%=cont.getPractice_no()%>'">
			&nbsp;&nbsp;&nbsp;
		<input type="button" value="삭제" onclick="
			if(confirm('삭제하시겠습니까?')){
				location.href='<%=request.getContextPath() %>/view/practice_delete.jsp?no=<%=cont.getPractice_no() %>'
			}else { return; }">
			&nbsp;&nbsp;&nbsp;
		<input type="button" value="글목록" onclick="location.href='<%=request.getContextPath() %>/select.do'">
	</div>

</body>
</html>