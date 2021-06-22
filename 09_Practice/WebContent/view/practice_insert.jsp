<%@page import="com.practice.model.PracticeDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<PracticeDTO> head = (List<PracticeDTO>)request.getAttribute("Head");
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
			<h3>PRACTICE TEXT INSERT</h3>
		<hr width="50%" color="pink">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath()%>/insertOk.do">
		<table border="1" cellspacing="0">
			<tr>
				<th>말머리</th>
				<td>
				<select name="head">
					<%
						if(head.size() != 0){
							for(int i=0; i<head.size(); i++){
								PracticeDTO dto = head.get(i);
					%>
					<option value="<%=dto.getPractice_head() %>"><%=dto.getPractice_head() %></option>
					<%		}
						}else {
					%>
					<option>::: 말머리 없음 :::</option>
					<%
						}
					%>
				</select>
				</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
			<tr>
				<th>내 용</th>
				<td><textarea rows="8" cols="30" name="content"></textarea><td>
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