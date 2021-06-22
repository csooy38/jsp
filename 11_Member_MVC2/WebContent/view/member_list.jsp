<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="tomato">
			<h3>MEMBER10 테이블 전체 회원 리스트</h3>
		<hr width="50%" color="tomato">
		<br><br>
		
		<table border="1" cellspacing="0" width="400" style="text-align:center;">
			<tr>	
				<th>번 호</th> <th>ID</th> <th>회원명</th> <th>가입일자</th>
			</tr>
			
			<c:set var="list" value="${List }"/>
			<c:if test="${!empty list }"> <%-- list가 비어있지 않다면 --%>
				<c:forEach items="${list }" var="dto"> <%-- 'list를 dto에 저장하라'를 반복 --%>
					<tr>
						<td> ${dto.getNum() } </td>
						<td> ${dto.getMemid() } </td>
						<td><a href="<%=request.getContextPath()%>/content.do?num=${dto.getNum()}">  
							${dto.getMemname() } </a></td>
						<td> ${dto.getRegdate().substring(0,10) } </td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 회원목록이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="회원등록" onclick="location.href='insert.do'">
				</td>
			</tr>
		</table>
	</div>

</body>
</html>