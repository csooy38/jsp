<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">

	/*
		Ajax 형식
		$.ajax({
			type : 통신 타입을 설정 - "get" 또는 "post"
			async : 비동기식으로 처리할지를 결정 - false인 경우 동기식으로 처리.
					생략시에는 비동기식으로 처리. (default:true)
			url : 요청할 url을 설정.
			data : 서버에 요청할 때 보낼 매개변수를 설정. - 서버로 전송할 데이터 변수.
			dataType : 응답을 받을 데이터의 타입을 설정. - text, xml, json 등.
			success : 요청 및 응답에 성공했을 때 처리할 내용을 설정.
			error : 요청 및 응답에 실패했을 때 처리할 내용을 설정.
			complete : 모든 작업을 마친 후 처리할 내용을 설정.
		});
	*/
	
	function process(){
		
		$.ajax({
			type: "get", 		 			// get방식으로 전송.
			dataType: "text",				// 응답 데이터를 테스트 형식으로 지정.
			url: "data/test.jsp",			// 전송할 페이지 지정.
			data: {param: "Hello Ajax!"}, 	// 전송할 매개변수와 값을 설정.
			success: function(data) {		// 전송과 응답이 성공했을 경우 작업을 설정하는 공간.
				$(".message").append(data);
			},
			error: function(data) {			// 전송과 응답이 실패했을 경우 작업을 설정하는 공간.
				alert("에러가 발생했습니다.");
			},
			complete: function(data) {		// 작업이 완료되었을 경우 수행할 작업을 설정하는 공간.
				alert("작업을 완료했습니다.");
			}
		});
		
	}

</script>
</head>
<body>

	<div align="center">
		<input type="button" value="전송하기" onclick="process()">
		<br><br>
		
		<div class="message">
		
		</div>
	</div>

</body>
</html>