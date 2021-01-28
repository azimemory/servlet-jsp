 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>if문</h1>
	
	<%	int num = (int)request.getAttribute("num");	
	
		if(num%2 == 0){ %>
		<h2>짝수 입니다. </h2>	
	<% 	}else{ %>
		<h2>홀수 입니다.</h2>
	<%	} %>
	
	
	<%-- 사용자가 입력한 값 : 5
		 결과 1/2/3/4/5  --%>
	
	<h1>for문</h1>
	<h2>1부터 사용자가 입력한 값까지의  숫자들을 출력해보기</h2>
	<h3>결과 : </h3>
	 <% 
		for(int i = 1; i <= num; i++){
			if(i == 1){
	 %>
			<span><%= i %></span>
		 <% }else { %>
			<span>/ <%= i %></span>
	 <% 	}
		} 
	 %>


</body>
</html>