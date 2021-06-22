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

<c:set var="dto" value="${cont }"></c:set>
	<div align="center">
		<hr width="50%" color="tomato">
			<h3>${dto.getMemname() } 회원 정보 상세 내역</h3>
		<hr width="50%" color="tomato">
		<br><br>
		
		<table border="1" cellspacing="0" width="350">
		
		<c:if test="${!empty dto }">
			<tr>
				<th>번 호</th> 
				<td>${dto.getNum() }</td>
			</tr>	
			
			<tr>
				<th>ID</th>
				<td>${dto.getMemid() }</td>
			</tr>
			
			<tr>
				<th>회원명</th> 
				<td>${dto.getMemname() }</td>
			</tr>
			
			<tr>
				<th>비밀번호</th> 
				<td>
					<c:if test="${dto.getPwd().length() != 0 }">
						<c:forEach begin="1" end="${dto.getPwd().length() }" var="i">
							*
						</c:forEach>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<th>나이</th> 
				<td>${dto.getAge() }</td>
			</tr>
			
			<tr>
				<th>마일리지</th> 
				<td>${dto.getMileage() }</td>
			</tr>
			
			<tr>
				<th>직업</th>
				<td>${dto.getJob() } </td>
			</tr>
			
			<tr>
				<th>주소</th> 
				<td>${dto.getAddr() }</td>
			</tr>
			
			<tr>
				<th>가입일자</th>
				<td>${dto.getRegdate().substring(0,10) }
			</tr>
		</c:if>
		<c:if test="${empty dto }">
			<tr>
				<td colspan="2" align="center">
					<h3>검색된 회원의 정보가 없습니다.</h3>
				</td>
			</tr>
		</c:if>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="회원수정" onclick="location.href='update.do?num=${dto.getNum() }'">
						&nbsp;&nbsp;&nbsp;
					<input type="button" value="회원삭제" 
						onclick="location.href='delete.do?num=${dto.getNum() }'">
						&nbsp;&nbsp;&nbsp;
					<input type="button" value="회원목록" onclick="location.href='<%=request.getContextPath() %>/select.do'">
				</td>
			</tr>
		</table>
	</div>

</body>
</html>