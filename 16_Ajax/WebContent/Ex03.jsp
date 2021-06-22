<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">

	$(function(){
		$.ajax({
			type: "get",
			url: "data/book.xml",
			dataType: "xml",
			success: function(data){
				// $(data) : $.ajax() 메서드가 book.xml 파일에서 불러 온 데이터 객체를 말한다.
				// find() : 메서드로 하위 book 태그를 찾는다.
				// each() : 반복함수. for문과 비슷한 역할.
				// 작성된 book 태그만큼 each() 함수를 이용하여 반복적으로 안의 내용을 수행.
				$(data).find("book").each(function() {
					// this : 현재의 book 객체를 의미. 
					var title = $("title", this).text();
					var author = $("author", this).text();
					var price = $("price", this).text();
					var txt = "<li>책 제목 : "+title+"</li>" +
							  "<li>책 저자 : "+author+"</li>"+
							  "<li>책 가격 : "+price+"</li><hr>";
					$("body").append(txt);
				});
			},
			error: function() {
				alert("데이터 통신 오류입니다.");
			}
		});
	});

</script>
</head>
<body>

</body>
</html>