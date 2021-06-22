<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="tomato">
		<h3>BOARD 게시글 수정</h3>
		<hr width="50%" color="tomato">
		<br>
		<br>

	<c:set var="dto" value="${edit }"></c:set>
		<form method="post"
			action="<%=request.getContextPath()%>/board_updateOk.do?no=${dto.getBoard_no() }">
			
			
			<input type="hidden" name="no" value="${dto.getBoard_no() }">
			
			<table border="1" cellspacing="0" width="300">			
					<tr>
						<th>글 번호</th>
						<td>${dto.getBoard_no() }</td>
					</tr>

					<tr>
						<th>제 목</th>
						<td><input type="text" name="title" value="${dto.getBoard_title() }"></td>
					</tr>

					<tr>
						<th>작성자</th>
						<td><input type="text" name="writer" value="${dto.getBoard_writer() }"></td>
					</tr>

					<tr>
						<th>조회수</th>
						<td>${dto.getBoard_hit() }</td>
					</tr>

					<tr>
						<th>내 용</th>
						<td><textarea cols="22" rows="8" name="context">${dto.getBoard_cont() }</textarea></td>
					</tr>
					
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pwd"></td>
					</tr>

					<tr>
						<th>작성일</th>
						<td>${dto.getBoard_regdate() }</td>
					</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit" value="수정">
						&nbsp;&nbsp;&nbsp; <input type="button" value="취소"
						onclick="location.href='<%=request.getContextPath() %>/board_content.do?no=${dto.getBoard_no() }'">
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>