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

	<jsp:include page="../include/admin_top.jsp"></jsp:include>
	
	<div align="center">
		<hr width="65%" color="blue">
			<h3>상품 수정 폼 페이지</h3>
		<hr width="65%" color="blue">
		<br>
		
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/admin_prod_edit_ok.do" >
			<c:set var="dto" value="${productDTO }"/>
			<c:set var="list" value="${categoryList }"/>
			<input type="hidden" name="p_num" value="${dto.getPnum() }">
			
			<table border="1" cellspacing="0" width="600">
				<c:if test="${!empty dto }">
					<tr>
						<th>카테고리</th>
						<td>
							<select name="p_category">
								<c:if test="${!empty list }">
									<c:forEach items="${list }" var="cdto">
										<c:if test="${dto.getPcategory_fk() eq cdto.getCategory_code() }">
											<option value="${cdto.getCategory_code() }" selected>${cdto.getCategory_name() }[${cdto.getCategory_code() }]</option>
										</c:if>
										
										<c:if test="${dto.getPcategory_fk() ne cdto.getCategory_code() }">
											<option value="${cdto.getCategory_code() }" disabled>${cdto.getCategory_name() }[${cdto.getCategory_code() }]</option>
										</c:if>
									</c:forEach>
								</c:if>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>상품명</th>
						<td><input name="p_name" value="${dto.getPname() }" readonly></td>
					</tr>
					
					<tr>	
						<th>제조사</th>
						<td><input name="p_company" value="${dto.getPcompany() }"></td>
					</tr>
					
					<tr>
						<th>상품 이미지</th>
						<td><img src="<%=request.getContextPath() %>/upload/${dto.getPimage() }" width="100" height="100"><br>
						<input type="file" name="p_image_new">
						<%-- 이미지를 수정하지 않고 그대로 사용할 경우에는 상품 등록시 입력한 이미지를 그대로 사용하여 히든으로 넘겨주자. --%>
						<input type="hidden" name="p_image_old" value="${dto.getPimage() }"></td>
					</tr>
						
					<tr>
						<th>상품 수량</th>
						<td><input type="number" value="${dto.getPqty() }" name="p_qty" min="1" max="100"></td>
					</tr>
					
					<tr>	
						<th>상품 가격</th>
						<td><input name="p_price" value="${dto.getPrice() }"></td>
					</tr>
					
					<tr>
						<th>상품 사양</th>
						<td>
							<select name="p_spec">
								<option value="none" <c:if test="${dto.getPspec() eq 'none' }"> selected </c:if>>일반</option>
								<option value="hit" <c:if test="${dto.getPspec() eq 'hit' }"> selected </c:if>>인기</option>
								<option value="new" <c:if test="${dto.getPspec() eq 'new' }"> selected </c:if>>최신</option>
								<option value="recommand" <c:if test="${dto.getPspec() eq 'recommand' }"> selected </c:if>>추천</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>상품 소개</th>
						<td><textarea rows="7" cols="30" name="p_content">${dto.getPcontents() }</textarea> </td>
					</tr>
					
					<tr>
						<th>상품 포인트</th>
						<td><input name="p_point" value="${dto.getPoint() }"></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="상품수정">
							<input type="reset" value="다시작성">
						</td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
	
	<jsp:include page="../include/admin_bottom.jsp"></jsp:include>

</body>
</html>