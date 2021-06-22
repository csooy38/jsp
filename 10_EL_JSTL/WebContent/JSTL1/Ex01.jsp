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

	<%--
		기본적인 JSTL 태그 ==> 출력을 할 때는 EL 언어를 사용
		
		1. 변수 태그 (set)
			<c:set var="변수명" value="값"> </c:set>
			예) <c:set var="su" value="10"> ==> int su = 10;
			예) <c:set var="str" valut="Hello"> ==> String str = "Hello";
		
		2. 출력 태그 (out)
			<c:out value="변수명"> </c:out>
			예) <c:out value="su"> ==> 10
			예) <c:out value="str"> ==> Hello
			
		3. 삭제 태그 (remove)
		 	<c:remove var="변수명"> </c:remove>
		 	예) <c:remove var="su">
		 	예) <c:remove var="str">
		 	
		4. 조건 처리 태그 (if문) ==> else문이 없음.
			<c:if test="조건식" var="변수명"> </c:if>
			
		5. 조건 처리 태그 (choose문) ==> switch~case문과 유사
			<c:choose> 
				<c:when test="조건식1">조건식1이 참인 경우 실행문</c:when>
				<c:when test="조건식2">조건식2이 참인 경우 실행문</c:when>
				<c:when test="조건식3">조건식3이 참인 경우 실행문</c:when>
				<c:otherwise>상기 조건식 이외의 경우 실행문</c:otherwise>
			</c:choose>
			
		6. 반복문 태그 (forEach문) : for문
			<c:forEach begin="시작값" end="끝값" step="증감값" var="변수명"></c:forEach>
			
			<c:forEach items="객체명" var="변수명"> 
	 --%>

	<h2>JSTL의 기본적인 태그들</h2>
	
	<h3>
		<%-- 1. 변수 태그 (set) --%>
		<c:set var="test" value="Hello JSTL!" />
		
		<%-- 2. 출력 태그 (out) --%>
		JSTL 값 출력 : <c:out value="test" /><br>
		
		JSTL 값 출력 : <c:out value="${test }"/><br>
		
		<%-- 3. 삭제 태그 (remove) --%>
		값 삭제 : <c:remove var="test"/><br>
		
		삭제 후 JSTL 값 출력 : <c:out value="${test }"/> <br>
		
		<%-- 4. 조건 처리 태그 (if문) ==> else문이 없음. --%>
		<c:if test="${10 > 5 }" var="k"/> 
		조건식 결과 >>> <c:out value="${k }"/>
		<br><hr>
		
		<%-- 5. 조건 처리 태그 (choose문) ==> switch~case문과 유사 --%>
		<%-- 학점 처리 작업 --%>
		<c:set var="grade" value="88" />
		<c:choose>
			<c:when test="${grade >=90 }">결과 : A학점</c:when>
			<c:when test="${grade >=80 }">결과 : B학점</c:when>
			<c:when test="${grade >=70 }">결과 : C학점</c:when>
			<c:when test="${grade >=60 }">결과 : D학점</c:when>
		</c:choose>
		<br><hr>
		
		<%-- 6. 반복문 태그 (forEach문) : for문 --%>
		<c:forEach begin="1" end="10" step="1" var="su">
			<c:out value="${su }"></c:out>
		</c:forEach>
		
		<hr>
		
		<%-- <c:forEach items="객체명" var="변수명">  --%>
		<%
			String[] str ={"홍길동", "이순신", "유관순", "김유신", "김연아"};
			pageContext.setAttribute("list", str);
		%>
		
		결과 >>> <c:forEach items="${list }" var="i">
					${i } &nbsp;&nbsp;&nbsp;
		</c:forEach>
	</h3>
	
	
	
	

</body>
</html>