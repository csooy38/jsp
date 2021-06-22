<%@page import="com.practice.model.PracticeDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<PracticeDTO> list = (List<PracticeDTO>)request.getAttribute("List");
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
			<h3>PRACTICE SEARCH LIST</h3>
		<hr width="50%" color="pink">
		<br><br>
		
		<table border="1" cellspacing="0" width="600" style="text-align:center;">
			<tr>
				<th>번호</th> <th>말머리</th> <th>제목</th> <th>작성자</th> <th>작성일</th> <th>조회</th>
			</tr>
			
			<%
				if(list.size() != 0) {
					for(int i=0; i<list.size(); i++) {
						PracticeDTO dto = list.get(i);
			%>
			<tr>
				<td><%=dto.getPractice_no() %></td>
				<td><%=dto.getPractice_head() %></td>
				<td><a href="<%=request.getContextPath()%>/content.do?no=<%=dto.getPractice_no()%>">
					<%=dto.getPractice_title() %></a></td>
				<td><%=dto.getPractice_writer() %></td>
				<td><%=dto.getPractice_regdate() %></td>
				<td><%=dto.getPractice_hit() %></td>
			</tr>
			<%
					}
				}else {
			%>
			<tr>
				<td colspan="6" align="center">
					<h3>작성된 게시글이 존재하지 않습니다.</h3>
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td colspan="6" align="right">
					<input type="button" value="처음으로" onclick="location.href='<%=request.getContextPath() %>/index.jsp'">
					<input type="button" value="글 목록" onclick="location.href='<%=request.getContextPath() %>/select.do'">
					<input type="button" value="글쓰기" onclick="location.href='<%=request.getContextPath() %>/insert.do'">
				</td>
			</tr>
		</table>
		<br><br>
		<form method="post" action="<%=request.getContextPath() %>/search.do">
			<select name="find_field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
				<option value="writer">작성자</option>
				<option value="head">말머리</option>
			</select>
			<input type="text" size="15" value="enter">
			<input type="submit" value="검색">
		</form>
		
	</div>

</body>
</html>