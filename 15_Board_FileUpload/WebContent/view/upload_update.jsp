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
			<h3>UPLOAD 테이블 자료실 게시물 수정 폼</h3>
		<hr width="50%" color="tomato">
		<br><br>
		
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/upload_update_ok.do">
		<table border="1" cellspacing="0" width="500">
			<c:set var="dto" value="${update }"/>
			<c:if test="${!empty dto }">
			<input type="hidden" name="upload_no" value="${dto.getUpload_no() }">
				<tr>	
					<th>작성자</th>
					<td><input type="text" name="upload_writer" value="${dto.getUpload_writer() }" readonly></td>
				</tr>
				
				<tr>
					<th>제 목</th>
					<td><input type="text" name="upload_title" value="${dto.getUpload_title() }"></td>
				</tr>
				
				<tr>
					<th>내 용</th>
					<td><textarea rows="7" cols="30" name="upload_cont">${dto.getUpload_cont() }</textarea></td>
				</tr>
				
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="upload_file"></td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="upload_pwd"></td>
				</tr>
			</c:if>
			
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>검색된 게시물이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기">
					<input type="button" value="취소" onclick="location.href='<%=request.getContextPath()%>/upload_cont.do?no=${dto.getUpload_no() }'">
					<input type="button" value="전체목록" onclick="location.href='<%=request.getContextPath()%>/upload_list.do'">
				</td>
			</tr>
		</table>
		</form>
	</div>

</body>
</html>