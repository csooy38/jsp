<%@page import="java.util.List"%>
<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<BoardDTO> search = (List<BoardDTO>)request.getAttribute("searchList");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="50%" color="red">
			<h3>BOARD 테이블 검색 게시물 목록</h3>
		<hr width="50%" color="red">
		<br><br>
		
		<table border="1" cellspacing="0" width="450" style="text-align:center;">
			<tr>
				<th>글번호</th> <th>글제목</th> <th>조회수</th> <th>작성일자</th>
			</tr>
			
			<%
				if(search.size() != 0) {
					// 검색된 레코드가 있는 경우 검색된 레코드 수만큼 반복해서 웹 브라우저에 출력
					for(int i=0; i<search.size(); i++) {
						BoardDTO dto = search.get(i);	
			%>
			<tr>
				<td><%=dto.getBoard_no() %></td>
				<td><a href="<%=request.getContextPath()%>/content.do?no=<%=dto.getBoard_no()%>">
					<%=dto.getBoard_title() %></a></td>
				<td><%=dto.getBoard_hit() %></td>
				<td><%=dto.getBoard_regdate().substring(0, 10) %></td>
			</tr>
			<%
					} // for문 end
				}else { // 검색된 레코드가 없는 경우
			%>
			<tr>
				<td colspan="4" align="center"><h3>검색된 게시물이 없습니다.</h3></td>
			</tr>			
			<%
				}
			%>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="전체 글목록" onclick="location.href='select.do'">
				</td>
			</tr>
		</table>
		
	</div>
	
</body>
</html>