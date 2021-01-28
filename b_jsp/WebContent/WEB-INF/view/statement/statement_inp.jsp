<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		사용자에게 정수를 하나 입력받아서 
		해당 정수가 홀수 인지 짝수인지 판단하는 서비스 -->
	<h1>짝수 홀수 판별하기</h1>
	<form action="/jsp/statement/calc" method="post">
		<label>
			짝수/홀수 가 궁금한 숫자를 입력하세요!<br>		
			<input type="number" name="num">
			<button>전송</button>
		</label>
	</form>
</body>
</html>