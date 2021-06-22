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

	<c:set var="dto" value="${Cont }"></c:set>

	<div align="center">
		<hr width="50%" color="tomato">
		<h3>${dto.getBoard_title() }</h3>
		<hr width="50%" color="tomato">
		<br> <br>

		<table border="1" cellspacing="0" width="350">
			<c:if test="${!empty dto }">
				<tr>
					<th>작성자</th>
					<td>${dto.getBoard_writer() }</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea cols="30" rows="15" readonly>${dto.getBoard_cont() }</textarea></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.getBoard_hit() }</td>
				</tr>
				<tr>
					<th>작성일자</th>
					<td>${dto.getBoard_regdate() }</td>
				</tr>
			</c:if>
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>글이 존재하지 않습니다.</h3>
					</td>
				</tr>
			</c:if>

			<tr>
				<td colspan="2" align="center"><input type="button" value="글수정"
					onclick="location.href='board_update.do?no=${dto.getBoard_no() }'">
					&nbsp;&nbsp;&nbsp; <input type="button" value="글삭제"
					onclick="location.href='board_delete.do?no=${dto.getBoard_no() }'">
					&nbsp;&nbsp;&nbsp; <input type="button" value="전체게시글"
					onclick="location.href='board_list.do'"></td>
			</tr>
		</table>
	</div>

</body>
</html>