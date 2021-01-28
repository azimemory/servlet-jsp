<%-- page 지시자 태그는 페이지마다 존재해야한다. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- include 지시자 태그를 사용해 공통 헤드를 추가 --%>    
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
	<meta name="description" content="include 공부중">
</head>
<body>
	<h1>메뉴 주문 시스템</h1>
	<h2>메뉴를 선택하세요</h2>
	<form action="${context}/menu/order" method="post">
		<select name="food" multiple="multiple" style="width:300px">
			<option value="피자">피자</option>
			<option value="햄버거">햄버거</option>
			<option value="치킨">치킨</option>
			<option value="회">회</option>
		</select>
		<button>전송</button>
	</form>
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>